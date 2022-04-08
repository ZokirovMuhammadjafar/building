package uz.karkas.building.dto.project;

import io.github.classgraph.Resource;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@Builder
public class ProjectDTO extends DTO {

    private Integer id;
    private String title;
    private String description;
    private Resource picturePath;

}
