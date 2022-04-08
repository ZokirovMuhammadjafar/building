package uz.karkas.building.dto.contact;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@Builder
public class ContactDTO extends DTO {

    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String message;


}
