package uz.karkas.building.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class ContactUpdateDTO extends DTO {

    @NotNull(message = "id not be null")
    private Integer id;

    @NotNull(message = "full name not be null")
    private String fullName;

    @NotNull(message = "phone number not be null")
    private String phoneNumber;

    @NotNull(message = "email not be null")
    private String email;

    @NotNull(message = "message not be null")
    private String message;


}
