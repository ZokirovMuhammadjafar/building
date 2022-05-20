package uz.karkas.building.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CategoryCreateDTO extends DTO {

    @NotNull(message = "name ru not be null")
    private String nameRU;

    @NotNull(message = "name uz not be null")
    private String nameUZ;

}
