package uz.karkas.building.dto.project;

import io.github.classgraph.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@AllArgsConstructor
public class ProjectCreateDTO extends DTO {

    private String title;
    private String description;
    private Resource picturePath;

}
