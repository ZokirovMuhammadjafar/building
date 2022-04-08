package uz.karkas.building.domain;


import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.colleges.CollegesDTO;
import uz.karkas.building.dto.product.ProductDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Colleges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameUZ;

    @Column(columnDefinition = "text")
    private String descriptionUZ;

    private String nameRU;

    @Column(columnDefinition = "text")
    private String descriptionRU;

    private String picturePath;

    public CollegesDTO get(String locale){
        if(locale.equals("uz")){
            return  CollegesDTO.builder().id(this.id).description(this.descriptionUZ).name(this.nameUZ).build();
        }else {
            return CollegesDTO.builder().description(this.descriptionRU).name(this.nameRU).build();
        }

    }

}
