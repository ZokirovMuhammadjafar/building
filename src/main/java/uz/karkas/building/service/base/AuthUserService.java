package uz.karkas.building.service.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.context.request.WebRequest;
import uz.karkas.building.domain.AuthUser;
import uz.karkas.building.dto.auth.AuthUserDto;
import uz.karkas.building.dto.auth.SessionDto;
import uz.karkas.building.exception.UserAlreadyTaken;
import uz.karkas.building.repository.AuthRepository;
import uz.karkas.building.response.ApiErrorDto;
import uz.karkas.building.response.Data;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthUserService implements UserDetailsService ,BaseGenericService {

    private final AuthRepository repository;
    private final PasswordEncoder encoder;
private final ObjectMapper objectMapper;
    public AuthUserService(AuthRepository repository, PasswordEncoder encoder, ObjectMapper objectMapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.find(true, username);

            AuthUser authUser = user;
            return User.builder().username(authUser.getUsername()).password(user.getPassword()).authorities(Arrays.asList(new SimpleGrantedAuthority(authUser.getRole().name()))).build();

    }

    public ResponseEntity<Data<SessionDto>> login(AuthUserDto dto, WebRequest request){
        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new
                    HttpPost("http://localhost:8080/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));
            JsonNode apiError = json_auth.get("apiError");
            ApiErrorDto errorDto = objectMapper.readValue(apiError.toString(), ApiErrorDto.class);
            if (errorDto==null) {
                JsonNode node = json_auth.get("body");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new Data<>(sessionDto),HttpStatus.OK);
            }
            return new ResponseEntity<>(new Data<>(ApiErrorDto.builder().message("bad request").path(request.getContextPath()).status(HttpStatus.BAD_REQUEST.value()).build()),HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new Data<>(ApiErrorDto.builder().message("bad request").path(request.getContextPath()).status( HttpStatus.INTERNAL_SERVER_ERROR.value()).build()),HttpStatus.OK);
        }
    }
    @Transactional
    public ResponseEntity<Data<Integer>> create(AuthUserDto dto) {
        AuthUser user=new AuthUser();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        try {
            Integer id = repository.save(user).getId();
            return new ResponseEntity<>(new Data<>(id),HttpStatus.OK);
        }catch (Exception e){
            throw new UserAlreadyTaken("username already taken");
        }

    }
}
