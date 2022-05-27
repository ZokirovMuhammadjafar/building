package uz.karkas.building;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uz.karkas.building.controller.swagger.ApiProperties;
import uz.karkas.building.controller.swagger.OpenApiProperties;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties({OpenApiProperties.class, ApiProperties.class})
public class BuildingApplication {


    public static void main(String[] args) {

        SpringApplication.run(BuildingApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
