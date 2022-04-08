package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.karkas.building.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, AbstractRepository {



}
