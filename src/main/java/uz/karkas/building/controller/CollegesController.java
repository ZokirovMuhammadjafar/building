package uz.karkas.building.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.colleges.CollegesCreateDTO;
import uz.karkas.building.dto.colleges.CollegesDTO;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.service.colleges.CollegesServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class CollegesController extends BaseController<CollegesServiceImpl> {

    private final FileService fileService;

    public CollegesController(CollegesServiceImpl service, FileService fileService) {
        super(service);
        this.fileService = fileService;
    }

    @GetMapping(PATH + "/colleges/get/{id}")
    public ResponseEntity<Data<CollegesDTO>> get(@PathVariable Integer id, @RequestHeader("accept-language") String language) {
        return service.get(id, language);
    }

    @PostMapping(PATH + "/colleges/create")
    public ResponseEntity<Data<Integer>> create(@RequestBody @Valid CollegesCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping(PATH + "/colleges/update")
    public ResponseEntity<Data<Boolean>> update(@RequestBody @Valid CollegesUpdateDTO dto, @RequestHeader("accept-language") String language) {
        return service.update(dto,language);

    }

    @DeleteMapping(PATH + "/colleges/delete/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Integer id) {

        return service.delete(id);
    }

    @GetMapping(PATH+"/colleges/all")
    public ResponseEntity<Data<List<CollegesDTO>>>getAll(@RequestHeader("accept-language") String language){
        return  service.getAll(language);
    }

}
