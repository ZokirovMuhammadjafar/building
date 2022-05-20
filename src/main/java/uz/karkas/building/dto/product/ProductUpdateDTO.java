package uz.karkas.building.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ProductUpdateDTO extends DTO {

    @NotNull(message = "id not be null")
    private Integer id;

    @NotNull(message = "name not be null")
    private String name;

    @NotNull(message = "description not be null")
    private String description;

    @NotNull(message = "category id not be null")
    private Integer categoryId;

    @NotNull(message = "picture id not be null")
    private Integer pictureId;

}
