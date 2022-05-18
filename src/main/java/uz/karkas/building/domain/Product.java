package uz.karkas.building.domain;

import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductDTO;

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

    private Integer fileId;

    public static Product create(ProductCreateDTO createDTO, Integer pictureId) {
        Product product=new Product();
        product.setCategoryId(createDTO.getCategoryId());
        product.setDescriptionRU(createDTO.getDescriptionRU());
        product.setDescriptionUZ(createDTO.getDescriptionUZ());
        product.setFileId(pictureId);
        product.setNameRU(createDTO.getNameRU());
        product.setNameUZ(createDTO.getNameUZ());
        return product;
    }

    public ProductDTO get(String locale){
        if(locale.equals("uz")){
            return  ProductDTO.builder().id(this.id).description(this.descriptionUZ).name(this.nameUZ).categoryId(this.categoryId).build();
        }else {
            return ProductDTO.builder().id(this.id).description(this.descriptionRU).name(this.nameRU).categoryId(this.categoryId).build();
        }

    }

}
