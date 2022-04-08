package uz.karkas.building.dto.colleges;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@AllArgsConstructor
public class CollegesCreateDTO extends DTO {

    private String nameUZ;
    private String descriptionUZ;
    private String nameRU;
    private String descriptionRU;
    private MultipartFile picture;

}
