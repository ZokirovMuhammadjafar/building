package uz.karkas.building.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryDTO;
import uz.karkas.building.dto.category.CategoryUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.service.category.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;
@CrossOrigin(value = "*")
@RestController
public class CategoryController extends BaseController<CategoryServiceImpl> {
    private final FileService fileService;

    public CategoryController(CategoryServiceImpl service, FileService fileService) {
        super(service);
        this.fileService = fileService;
    }

    @GetMapping(value = PATH + "/category/get/{id}")
    public ResponseEntity<Data<CategoryDTO>> get(@RequestHeader("accept-language") String language, @PathVariable Integer id) {
        return service.get(id, language);
    }

    @PostMapping(value = PATH + "/category/create")
    public ResponseEntity<Data<Integer>> create(@RequestBody CategoryCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping(value = PATH + "/category/update")
    public ResponseEntity<Data<Boolean>> update(@RequestHeader("accept-language") String language, @RequestBody CategoryUpdateDTO dto) {
        return service.update(dto, language);
    }

    @DeleteMapping(value = PATH + "/category/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = PATH + "/category/all")
    public ResponseEntity<Data<List<CategoryDTO>>> getAll(@RequestHeader("accept-language") String language) {
        return service.getAll(language);
    }


}
