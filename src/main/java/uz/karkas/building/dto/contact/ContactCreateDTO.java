package uz.karkas.building.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
public class ContactCreateDTO extends DTO {

    @NotBlank
    private String fullName;
    @Pattern(regexp = "[+](998)[0-9]{9}")
    private String phoneNumber;
//    @Pattern(regexp = "[\\dA-Za-z]+@[a-z]{2,10}[.][a-z]{2,5}")
    private String email;
    @NotBlank
    private String message;

}
