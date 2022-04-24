package uz.karkas.building.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Setter
@Getter
@AllArgsConstructor
public class ContactCreateDTO extends DTO {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String message;

}
