package uz.karkas.building.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.karkas.building.domain.Project;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer>, BaseRepository {

    @Modifying
    @Query(value = "update project set descriptionuz =  ?1 , titleuz = ?2 where id = ?3 ",nativeQuery = true)
    boolean updateUZ(String description,String title,Integer id);

    @Modifying
    @Query(value = "update project set descriptionru = ?1 , titleru = ?2 where id= ?3 ",nativeQuery = true)
    void updateRU(String description,String title,Integer id);

    List<Project>findAllByOrderByIdDesc(Pageable pageable);
}
