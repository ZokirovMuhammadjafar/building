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
    @Query(value = "update category  set nameuz = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}", nativeQuery = true)
    void updateUZ(CategoryUpdateDTO updateDTO);

    @Transactional
    @Modifying
    @Query(value = "update category  set nameru = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}", nativeQuery = true)
    void updateRU(CategoryUpdateDTO updateDTO);

    List<Category> findAllByOrderByIdDesc(Pageable pageable);
}
