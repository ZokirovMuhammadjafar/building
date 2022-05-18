package uz.karkas.building.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.contact.ContactCreateDTO;
import uz.karkas.building.dto.contact.ContactDTO;
import uz.karkas.building.dto.contact.ContactUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.contact.ContactService;
import uz.karkas.building.service.contact.ContactServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
public class ContactController extends BaseController<ContactServiceImpl> {

    public ContactController(ContactServiceImpl service) {
        super(service);
    }

    @PostMapping(PATH + "/contect/create")
    public ResponseEntity<Data<Integer>> create(@RequestBody @Valid ContactCreateDTO dto) {
        return service.create(dto);
    }


    @GetMapping(PATH + "/contact/get/{id}")
    public ResponseEntity<Data<ContactDTO>> get(@PathVariable Integer id) {
        return service.get(id, "");
    }

    @GetMapping(PATH + "/contact/all")
    public ResponseEntity<Data<List<ContactDTO>>> getAll() {
        return service.getAll("");
    }

    @DeleteMapping(PATH + "/contact/delete/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
