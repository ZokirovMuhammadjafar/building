package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.karkas.building.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>, BaseRepository {

}
