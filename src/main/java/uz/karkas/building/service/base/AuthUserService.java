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
import uz.karkas.building.config.swagger.ApiProperties;
import uz.karkas.building.domain.AuthUser;
import uz.karkas.building.dto.auth.AuthUserDto;
import uz.karkas.building.dto.auth.SessionDto;
import uz.karkas.building.enums.Role;
import uz.karkas.building.exception.UserAlreadyTaken;
import uz.karkas.building.repository.AuthRepository;
import uz.karkas.building.response.ApiErrorDto;
import uz.karkas.building.response.Data;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
public class AuthUserService implements UserDetailsService ,BaseGenericService {

    private final AuthRepository repository;
    private final PasswordEncoder encoder;
    private final ApiProperties properties;
private final ObjectMapper objectMapper;
    public AuthUserService(AuthRepository repository, PasswordEncoder encoder, ApiProperties properties, ObjectMapper objectMapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.properties = properties;
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
            System.out.println(properties.getRequest()+properties.getApi());
            HttpPost httppost = new
                    HttpPost(properties.getRequest()+properties.getApi()+"/api/login");

            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));
            JsonNode apiError = json_auth.get("apiError");

            if (apiError==null) {
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
        user.setActive(true);
        user.setBlock(false);
        user.setRole(Role.ADMIN);
        try {
            Integer id = repository.save(user).getId();
            return new ResponseEntity<>(new Data<>(id),HttpStatus.OK);
        }catch (Exception e){
            throw new UserAlreadyTaken("username already taken");
        }

    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
