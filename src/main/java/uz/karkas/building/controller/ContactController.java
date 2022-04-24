package uz.karkas.building.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.contact.ContactCreateDTO;
import uz.karkas.building.dto.contact.ContactDTO;
import uz.karkas.building.dto.contact.ContactUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.contact.ContactService;
import uz.karkas.building.service.contact.ContactServiceImpl;

import java.util.List;

@RestController
public class ContactController extends BaseController<ContactServiceImpl> {

    public ContactController(ContactServiceImpl service) {
        super(service);
    }

    @PostMapping(PATH+"/contect/create")
    public ResponseEntity<Data<Integer>>create(@RequestBody ContactCreateDTO dto){
        return service.create(dto);
    }

    @PutMapping(PATH+"/contact/update")
    public ResponseEntity<Data<Boolean>>update(@RequestBody ContactUpdateDTO dto){
        return  service.update(dto,"");
    }

    @GetMapping(PATH+"/contact/get/{id}")
    public ResponseEntity<Data<ContactDTO>>get(@PathVariable Integer id){
        return service.get(id,"");
    }

    @GetMapping(PATH+"/contact/all")
    public ResponseEntity<Data<List<ContactDTO>>>getAll(){
        return service.getAll("");
    }

    @DeleteMapping(PATH+"/contact/delete/{id}")
    public ResponseEntity<Data<Void>>delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
