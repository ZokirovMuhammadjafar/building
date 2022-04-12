package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.karkas.building.domain.Category;
import uz.karkas.building.dto.category.CategoryUpdateDTO;

import javax.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer>, BaseRepository {

    @Transactional
    @Modifying
    @Query(value = "update category  set nameuz = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}",nativeQuery = true)
    Boolean updateUZ(CategoryUpdateDTO updateDTO);

    @Transactional
    @Modifying
    @Query(value = "update category  set nameru = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}",nativeQuery = true)
    Boolean updateRU(CategoryUpdateDTO updateDTO);

}
