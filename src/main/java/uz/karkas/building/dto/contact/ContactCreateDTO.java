package uz.karkas.building.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
public class ContactCreateDTO extends DTO {

    @NotBlank
    @NotNull(message = "full name not be null")
    private String fullName;

    @NotNull(message = "phone number not be null")
    @Pattern(regexp = "[+](998)[0-9]{9}")
    private String phoneNumber;

    @NotNull(message = "email not be null")
    private String email;

    @NotBlank
    @NotNull(message = "message not be null")
    private String message;

}
