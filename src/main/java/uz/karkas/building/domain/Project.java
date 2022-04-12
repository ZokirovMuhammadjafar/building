package uz.karkas.building.domain;

import lombok.Getter;
import lombok.Setter;
import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titleUZ;

    @Column(columnDefinition = "text")
    private String descriptionUZ;

    private String titleRU;

    @Column(columnDefinition = "text")
    private String descriptionRU;

    private Integer fileId;

    public static Project create(ProjectCreateDTO createDTO, Integer pictureId) {
        Project project=new Project();
        project.setDescriptionRU(createDTO.getDescriptionRU());
        project.setDescriptionUZ(createDTO.getDescriptionUZ());
        project.setFileId(pictureId);
        project.setTitleUZ(createDTO.getTitleUZ());
        project.setTitleRU(createDTO.getTitleRU());
        return project;
    }

    public ProjectDTO get(String locale){
        if(locale.equals("uz")){
            return  ProjectDTO.builder().id(this.id).description(this.descriptionUZ).title(this.titleUZ).build();
        }else {
            return ProjectDTO.builder().id(this.id).description(this.descriptionRU).title(this.titleRU).build();
        }

    }

}
