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
public class CollegesUpdateDTO extends DTO {

    @NotNull(message = "id not be null")
    private Integer id;

    @NotNull(message = "name not be null")
    private String name;

    @NotNull(message = "description not be null")
    private String description;

    @NotNull(message = "picture id not be null")
    private Integer pictureId;

}
