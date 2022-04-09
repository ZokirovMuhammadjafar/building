package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.karkas.building.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>, BaseRepository {



}
