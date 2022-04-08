package uz.karkas.building.dto.project;

import io.github.classgraph.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@AllArgsConstructor
public class ProjectCreateDTO extends DTO {

    private String titleUZ;
    private String titleRU;
    private String descriptionUZ;
    private String descriptionRU;
    private MultipartFile picturePath;

}
