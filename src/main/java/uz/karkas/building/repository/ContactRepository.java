package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.karkas.building.domain.Contact;
import uz.karkas.building.dto.contact.ContactUpdateDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer>, BaseRepository {


@Modifying
@Transactional
@Query(value = "update contact c set c.email = #{#updateDTO.email}, c.fullName=#{#updateDTO.fullName}, c.message= #{#updateDTO.message}, c.phoneNumber = #{#updateDTO.phoneNumber} where c.id =#{#updateDTO.id} returnig true",nativeQuery = true)
    boolean updateUZ(ContactUpdateDTO updateDTO);

List<Contact>findAllByOrderByIdDesc();

}
