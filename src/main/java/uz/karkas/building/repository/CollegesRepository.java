package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.karkas.building.domain.Colleges;

public interface CollegesRepository extends JpaRepository<Colleges, Integer>, AbstractRepository {



}
