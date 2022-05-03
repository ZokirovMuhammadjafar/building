package uz.karkas.building.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.karkas.building.domain.Colleges;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface CollegesRepository extends JpaRepository<Colleges, Integer>, BaseRepository {


    @Modifying
    @Transactional
    @Query(value = "update colleges set descriptionuz = #{#updateDTO.description} , nameuz = #{#updateDTO.name} where id= #{#updateDTO.id}",nativeQuery = true)
   void updateUZ(CollegesUpdateDTO updateDTO);

    @Modifying
    @Transactional
    @Query(value = "update colleges set descriptionru = #{#updateDTO.description} , nameru = #{#updateDTO.name} where id= #{#updateDTO.id} ",nativeQuery = true)
   void updateRU(CollegesUpdateDTO updateDTO);

    @Query(value = "from colleges order by id desc limit 10 ", nativeQuery = true)
    List<Colleges>findTen();

    List<Colleges> findByOrderByIdDesc(Pageable pageable);
}
