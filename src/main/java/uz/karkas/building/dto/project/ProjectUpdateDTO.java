package uz.karkas.building.dto.project;

import io.github.classgraph.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@AllArgsConstructor
public class ProjectUpdateDTO extends DTO {

    private Integer id;
    private String title;
    private String description;
    private Integer pictureId;

}
