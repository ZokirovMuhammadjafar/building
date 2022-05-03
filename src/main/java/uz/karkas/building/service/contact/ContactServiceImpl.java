package uz.karkas.building.service.contact;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.karkas.building.domain.Contact;
import uz.karkas.building.dto.contact.ContactCreateDTO;
import uz.karkas.building.dto.contact.ContactDTO;
import uz.karkas.building.dto.contact.ContactUpdateDTO;
import uz.karkas.building.repository.ContactRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.validator.contact.ContactValidator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl extends AbstractService<ContactRepository, ContactValidator> implements ContactService {

    protected ContactServiceImpl(ContactValidator validator, ContactRepository repository) {
        super(validator, repository);
    }

    @Override
    public ResponseEntity<Data<Integer>> create(ContactCreateDTO createDTO)  {
        Contact contact = new Contact(createDTO.getFullName(), createDTO.getPhoneNumber(), createDTO.getEmail(), createDTO.getMessage());
        Integer id = repository.save(contact).getId();
        return new ResponseEntity<>(new Data<>(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(ContactUpdateDTO updateDTO, String language) {

        boolean a=repository.updateUZ(updateDTO);

        return new ResponseEntity<>(new Data<>(a),HttpStatus.OK);
    }

    @Override
    public ResponseEntity.HeadersBuilder<?> delete(Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent();
    }

    @Override
    public ResponseEntity<Data<ContactDTO>> get(Integer id, String language) {
        Optional<Contact> byId = repository.findById(id);
        if(byId.isPresent()){
            return new ResponseEntity<>(new Data<>(byId.get().get()),HttpStatus.OK);
        }
        throw new RuntimeException("not found");
    }

    @Override
    public ResponseEntity<Data<List<ContactDTO>>> getAll(String language) {
        Pageable pageable=Pageable.ofSize(10);
        List<ContactDTO> collect = repository.findAllByOrderByIdDesc(pageable).stream().map(Contact::get).limit(10).collect(Collectors.toList());
        return new ResponseEntity<>(new Data<>(collect),HttpStatus.OK);
    }
}
