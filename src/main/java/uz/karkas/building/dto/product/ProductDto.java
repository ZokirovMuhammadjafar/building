package uz.karkas.building.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Resource picture;


}
