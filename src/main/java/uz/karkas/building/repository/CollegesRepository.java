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
    @Query(value = "update colleges set descriptionuz = ?3 , nameuz = ?2, file_id= ?4 where id= ?1",nativeQuery = true)
   void updateUZ(Integer id, String name, String description, Integer pictureId);

    @Modifying
    @Transactional
    @Query(value = "update colleges set descriptionru = ?3 , nameru = ?2, file_id= ?4 where id= ?1 ",nativeQuery = true)
   void updateRU(Integer id, String name, String description, Integer pictureId);

    @Query(value = "from colleges order by id desc limit 10 ", nativeQuery = true)
    List<Colleges>findTen();

    List<Colleges> findByOrderByIdDesc(Pageable pageable);
}
