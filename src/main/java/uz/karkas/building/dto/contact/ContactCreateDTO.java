package uz.karkas.building.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Setter
@Getter
@AllArgsConstructor
public class ContactCreateDTO extends DTO {

    public String fullName;
    public String phoneNumber;
    public String email;
    public String message;

}
