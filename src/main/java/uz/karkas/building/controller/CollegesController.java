package uz.karkas.building.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.colleges.CollegesCreateDTO;
import uz.karkas.building.dto.colleges.CollegesDTO;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.colleges.CollegesServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CollegesController extends BaseController<CollegesServiceImpl> {


    public CollegesController(CollegesServiceImpl service) {
        super(service);
    }

    @GetMapping(PATH + "/colleges/{id}")
    public ResponseEntity<Data<CollegesDTO>> get(@PathVariable Integer id, @RequestHeader("accept-language") String language) {
        return service.get(id, language);
    }

    @PostMapping(PATH + "/colleges/create}")
    public ResponseEntity<Data<Integer>> create(@RequestBody @Valid CollegesCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping(PATH + "/colleges/update")
    public ResponseEntity<Data<Boolean>> update(@RequestBody @Valid CollegesUpdateDTO dto, @RequestHeader("accept-language") String language) {
        return service.update(dto,language);

    }

    @DeleteMapping(PATH + "/colleges/delete/{id}")
    public ResponseEntity<Data<Void>> delete(@PathVariable Integer id) {

        return service.delete(id);
    }

    @GetMapping(PATH+"/colleges/getAll")
    public ResponseEntity<Data<List<CollegesDTO>>>getAll(@RequestHeader("accept-language") String language){
        return  service.getAll(language);
    }

}
