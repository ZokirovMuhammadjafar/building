package uz.karkas.building.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthUserDto implements Serializable {
    private String username;
    private String password;


}
