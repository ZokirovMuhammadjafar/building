package uz.karkas.building.dto.product;

import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.base.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

public class ProductCreateDTO extends DTO {


//    @Size(min = 5,max = 50)
    @NotBlank
    private String nameUZ;
//    @Size(min = 5,max = 50)
    @NotBlank
    private String nameRU;
//    @Size(min = 10,max = 500)
    @NotBlank
    private String descriptionUZ;
//    @Size(min = 10,max = 500)
    @NotBlank
    private String descriptionRU;


    @NotNull
    private Integer categoryId;

    private Integer pictureId;

}
