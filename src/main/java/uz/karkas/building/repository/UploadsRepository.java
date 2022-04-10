package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.karkas.building.domain.Uploads;

public interface UploadsRepository extends JpaRepository<Uploads,Integer> {
}
