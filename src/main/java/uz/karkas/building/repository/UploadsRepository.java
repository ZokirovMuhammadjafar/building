package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.karkas.building.domain.Uploads;

import java.util.Optional;

public interface UploadsRepository extends JpaRepository<Uploads, Integer> {
    Optional<Uploads> findByPathName(String id);

    @Query(value = "insert into files(bytes) values ( ?1 ) returning id", nativeQuery = true)
    Integer savefile(byte[] bytes);

    @Query(value = "select bytes from files where id = ?1", nativeQuery = true)
    byte[] take(Integer fileId);
}
