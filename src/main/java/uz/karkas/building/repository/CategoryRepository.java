package uz.karkas.building.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.karkas.building.domain.Category;
import uz.karkas.building.dto.category.CategoryUpdateDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer>, BaseRepository {

    @Transactional
    @Modifying
    @Query(value = "update category  set nameuz = ?2 where id = ?1", nativeQuery = true)
    void updateUZ(Integer id, String name);

    @Transactional
    @Modifying
    @Query(value = "update category  set nameru = ?2 where id = ?1", nativeQuery = true)
    void updateRU(Integer id, String name);

    List<Category> findAllByOrderByIdDesc(Pageable pageable);
}
