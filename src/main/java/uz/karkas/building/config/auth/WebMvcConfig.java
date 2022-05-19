package uz.karkas.building.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/user/integration").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
        registry.addMapping("/api/user/integration/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }

}
