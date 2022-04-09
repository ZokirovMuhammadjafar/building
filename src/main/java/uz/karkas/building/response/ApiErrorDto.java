package uz.karkas.building.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiErrorDto {
    private String message;
    private String developerMessage;
    private String path;
    private Integer status;
    private Timestamp date =Timestamp.valueOf(LocalDateTime.now());
}
