package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.karkas.building.domain.Project;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;

public interface ProjectRepository extends JpaRepository<Project, Integer>, BaseRepository {

    @Modifying
    @Query(value = "update project set descriptionuz = #{#updateDTO.description} , titleuz = #{#updateDTO.title} where id= #{#updateDTO.id} returning true",nativeQuery = true)
    boolean updateUZ(ProjectUpdateDTO updateDTO);

    @Modifying
    @Query(value = "update project set descriptionru = #{#updateDTO.description} , titleru = #{#updateDTO.title} where id= #{#updateDTO.id} returning true",nativeQuery = true)
    boolean updateRU(ProjectUpdateDTO updateDTO);

}
