package uz.karkas.building;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import uz.karkas.building.config.swagger.OpenApiProperties;

import java.util.Locale;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties({OpenApiProperties.class})
public class BuildingApplication {



    public static void main(String[] args) {

        SpringApplication.run(BuildingApplication.class, args);
    }

}
