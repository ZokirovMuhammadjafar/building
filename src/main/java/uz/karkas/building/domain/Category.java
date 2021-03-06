package uz.karkas.building.domain;

import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameUZ;

    private String nameRU;

    public static Category create(CategoryCreateDTO dto){

        Category category = new Category();
        category.setNameUZ(dto.getNameUZ());
        category.setNameRU(dto.getNameRU());

        return category;

    }

    public CategoryDTO get(String locale){
        if("uz".equalsIgnoreCase(locale)){
            return CategoryDTO.builder().name(this.nameUZ).id(this.id).build();
        }else {
            return CategoryDTO.builder().name(this.nameRU).id(this.id).build();
        }
    }
}
