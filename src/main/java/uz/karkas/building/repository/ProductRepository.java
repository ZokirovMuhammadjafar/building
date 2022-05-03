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
    @Query(value = "update product  set descriptionuz = #{#updateDTO.getDescription()}, nameuz = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}",nativeQuery = true)
    void  updateUZ(ProductUpdateDTO updateDTO);

    @Transactional
    @Modifying
    @Query(value = "update product set descriptionru = #{#updateDTO.getDescription()}, nameru = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}",nativeQuery = true)
    void updateRU(ProductUpdateDTO updateDTO);

    List<Product>findAllByOrderByIdDesc(Pageable pageable);
}
