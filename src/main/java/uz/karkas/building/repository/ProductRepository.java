package uz.karkas.building.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.karkas.building.domain.Product;
import uz.karkas.building.dto.product.ProductUpdateDTO;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, BaseRepository {


    @Transactional
    @Modifying
    @Query(value = "update product  set descriptionuz = ?3, nameuz = ?2, category_id= ?4, file_id = ?5 where id = ?1",nativeQuery = true)
    void  updateUZ(Integer id, String name, String description, Integer categoryId, Integer pictureId);

    @Transactional
    @Modifying
    @Query(value = "update product set descriptionru = ?3, nameru = ?2, category_id= ?4, file_id = ?5 where id = ?1",nativeQuery = true)
    void updateRU(Integer id, String name, String description, Integer categoryId, Integer pictureId);

    List<Product>findAllByOrderByIdDesc(Pageable pageable);

    @Transactional
    @Query(value = "select * from product where category_id = ?1 order by category_id",nativeQuery = true)
    List<Product>findAllByCategory(Integer categoryId);

    @Query(value = "update product set category_id = 0 where category_id = ?1", nativeQuery = true)
    void updateAfterDeleteCategory(Integer categoryId);

}
