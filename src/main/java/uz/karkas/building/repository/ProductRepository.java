package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.karkas.building.domain.Product;
import uz.karkas.building.dto.product.ProductUpdateDTO;

import javax.transaction.Transactional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, BaseRepository {


    @Transactional
    @Modifying
    @Query(value = "update product  set descriptionuz = #{#updateDTO.getDescription()}, nameuz = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}",nativeQuery = true)
    Boolean updateUZ(ProductUpdateDTO updateDTO);

    @Transactional
    @Modifying
    @Query(value = "update product set descriptionru = #{#updateDTO.getDescription()}, nameru = #{#updateDTO.getName()} where id = #{#updateDTO.getId()}",nativeQuery = true)
    Boolean updateRU(ProductUpdateDTO updateDTO);
}
