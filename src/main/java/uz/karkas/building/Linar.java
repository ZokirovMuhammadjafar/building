package uz.karkas.building;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import uz.karkas.building.dto.auth.AuthUserDto;
import uz.karkas.building.service.base.AuthUserService;

@Component
public class Linar implements CommandLineRunner {
    private final AuthUserService service;

    public Linar(AuthUserService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO: 13/04/2022 create yaratish va structuraga solish
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setUsername("admin");
        authUserDto.setPassword("password");
       try {
           service.create(authUserDto);
       }catch (RuntimeException e){
           System.out.println(e.getMessage());
       }
    }
}
