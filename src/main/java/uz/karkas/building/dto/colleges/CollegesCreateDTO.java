package uz.karkas.building.dto.colleges;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CollegesCreateDTO extends DTO {

    @NotNull(message = "name uz not be null")
    private String nameUZ;

    @NotNull(message = " description uz not be null")
    private String descriptionUZ;

    @NotNull(message = "name ru not be null")
    private String nameRU;

    @NotNull(message = "description ru not be null")
    private String descriptionRU;

    @NotNull(message = "pricture id not be null")
    private Integer pictureId;

}
