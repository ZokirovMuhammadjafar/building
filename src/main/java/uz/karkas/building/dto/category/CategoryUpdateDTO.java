package uz.karkas.building.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@AllArgsConstructor
public class CategoryUpdateDTO extends DTO {

    private Integer id;
    private String name;


}