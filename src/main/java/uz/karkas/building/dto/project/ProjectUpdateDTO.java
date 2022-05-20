package uz.karkas.building.dto.project;

import io.github.classgraph.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ProjectUpdateDTO extends DTO {

    @NotNull(message = "id not be null")
    private Integer id;

    @NotNull(message = "title not be null")
    private String title;

    @NotNull(message = "description not be null")
    private String description;

    @NotNull(message = "picture id not be null")
    private Integer pictureId;

}
