package uz.karkas.building.dto.colleges;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import uz.karkas.building.dto.base.DTO;

@Getter
@Setter
@AllArgsConstructor
public class CollegesUpdateDTO extends DTO {

    private Integer id;
    private String name;
    private String description;
    private Resource picture;

}
