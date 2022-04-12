package uz.karkas.building.config.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "path")
public class ApiProperties {
    private String request;
    private String api;
    private String urlPath;
    private String filePath;


}
