package uz.karkas.building.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.karkas.building.service.base.AuthUserService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    public final static String[] WHITE_LIST = {

            "/api/login"
            ,"/api/v1/download/**"
            ,"/actuator/**"
            ,"/api/v1/category/get/**"
            ,"/api/v1/category/all"
            ,"/api/v1/colleges/get/**"
            ,"/api/v1/colleges/all"
            ,"/api/v1/product/get/**"
            ,"/api/v1/product/all"
            ,"/api/v1/project/get/**"
            ,"/api/v1/project/all"
            ,"/api/v1/auth/login"
            ,"/swagger-ui/**",
            "/api/docs/**"
    };

    private final AuthUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest().authenticated();

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
