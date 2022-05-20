package uz.karkas.building.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CategoryUpdateDTO extends DTO {

    @NotNull(message = "id not be null")
    private Integer id;
    @NotNull(message = "name not be null")
    private String name;


}
