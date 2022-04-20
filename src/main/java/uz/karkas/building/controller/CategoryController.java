package uz.karkas.building.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryDTO;
import uz.karkas.building.dto.category.CategoryUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.service.category.CategoryServiceImpl;

import java.util.List;

@RestController
public class CategoryController extends BaseController<CategoryServiceImpl>{

    public CategoryController(CategoryServiceImpl service) {
        super(service);
    }

    @GetMapping(value = PATH + "/category/get/{id}")
    public ResponseEntity<Data<CategoryDTO>> get(@RequestHeader("accept-language") String language, @PathVariable Integer id) {
        return service.get(id, language);
    }

    @PostMapping(value = PATH + "/category/create/")
    public ResponseEntity<Data<Integer>> create( CategoryCreateDTO dto) {
        return service.create(dto);
    }


    @PutMapping (value = PATH + "/category/update")
    public ResponseEntity<Data<Boolean>> update(@RequestHeader("accept-language") String language, @RequestBody CategoryUpdateDTO dto) {
        return service.update(dto, language);
    }
    @DeleteMapping(value = PATH + "/category/delete/{id}")
    public ResponseEntity<Data<Void>>delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @DeleteMapping(value = PATH + "/category/all")
    public ResponseEntity<Data<List<CategoryDTO>>> getAll(@RequestHeader("accept-language") String language) {
        return service.getAll(language);
    }


}
