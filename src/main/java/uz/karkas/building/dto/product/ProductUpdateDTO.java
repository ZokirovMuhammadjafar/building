package uz.karkas.building.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ProductUpdateDTO extends DTO {

//    @NotBlank
    private Integer id;
//    @Size(min = 5,max = 50)
    private String name;
//    @Size(min = 10,max = 500)
    private String description;
//    private Resource picture;

}
