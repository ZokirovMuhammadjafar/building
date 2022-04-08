package uz.karkas.building.domain;

import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.product.ProductDto;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameUZ;

    @Column(columnDefinition = "text")
    private String descriptionUZ;

    private String nameRU;

    @Column(columnDefinition = "text")
    private String descriptionRU;

    private Integer categoryId;

    private String picturePath;

    public ProductDto get(String locale){
        if(locale.equals("uz")){
            return  ProductDto.builder().id(this.id).description(this.descriptionUZ).name(this.nameUZ).build();
        }else {
            return ProductDto.builder().description(this.descriptionRU).name(this.nameRU).build();
        }

    }

}
