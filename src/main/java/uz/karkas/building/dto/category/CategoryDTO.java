package uz.karkas.building.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@Builder
public class CategoryDTO extends DTO {

    private Integer id;
    private String name;

}
