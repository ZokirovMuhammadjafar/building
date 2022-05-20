package uz.karkas.building.dto.project;

import io.github.classgraph.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ProjectCreateDTO extends DTO {

    @NotNull(message = "title uz not be null")
    private String titleUZ;

    @NotNull(message = "title ru not be null")
    private String titleRU;

    @NotNull(message = "description uz not be null")
    private String descriptionUZ;

    @NotNull(message = "descriotion ru not be null")
    private String descriptionRU;

    @NotNull(message = "pitcture id not be null")
    private Integer pictureId;

}
