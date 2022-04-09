package uz.karkas.building.service.contact;

import org.springframework.http.ResponseEntity;
import uz.karkas.building.dto.contact.ContactCreateDTO;
import uz.karkas.building.dto.contact.ContactDTO;
import uz.karkas.building.dto.contact.ContactUpdateDTO;
import uz.karkas.building.repository.ContactRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.validator.contact.ContactValidator;

import java.util.List;

public class ContactServiceImpl extends AbstractService<ContactRepository, ContactValidator> implements ContactService {

    protected ContactServiceImpl(ContactValidator validator, ContactRepository repository) {
        super(validator, repository);
    }

    @Override
    public ResponseEntity<Data<Integer>> create(ContactCreateDTO createDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(ContactUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<ContactDTO>> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<ContactDTO>>> getAll() {
        return null;
    }
}
