package uz.karkas.building.dto.product;

import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class ProductCreateDTO extends DTO {

    @NotBlank
    @NotNull(message = "name uz not be null")
    private String nameUZ;

    @NotBlank
    @NotNull(message = "name ru not be null")
    private String nameRU;

    @NotBlank
    @NotNull(message = "description uz not be null")
    private String descriptionUZ;

    @NotBlank
    @NotNull(message = "description ru not be null")
    private String descriptionRU;

    @NotNull(message = "category id not be null")
    @NotNull
    private Integer categoryId;

    @NotNull(message = "picture id not be null")
    private Integer pictureId;

}
