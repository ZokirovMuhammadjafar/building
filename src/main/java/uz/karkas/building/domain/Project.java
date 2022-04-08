package uz.karkas.building.domain;

import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.project.ProjectDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Project {

    @Id
    private Integer id;

    private String titleUZ;

    @Column(columnDefinition = "text")
    private String descriptionUZ;

    private String titleRU;

    @Column(columnDefinition = "text")
    private String descriptionRU;

    private String picturePath;

    public ProjectDTO get(String locale){
        if(locale.equals("uz")){
            return  ProjectDTO.builder().id(this.id).description(this.descriptionUZ).title(this.titleUZ).build();
        }else {
            return ProjectDTO.builder().id(this.id).description(this.descriptionRU).title(this.titleRU).build();
        }

    }

}
