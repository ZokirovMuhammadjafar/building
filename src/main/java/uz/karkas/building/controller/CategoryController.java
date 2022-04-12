package uz.karkas.building.controller;

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

    public CategoryController(CategoryServiceImpl service, FileService fileService) {
        super(service, fileService);
    }

    @GetMapping(value = PATH + "/category/{id}")
    public ResponseEntity<Data<CategoryDTO>> get(@RequestHeader("accept-language") String language, @PathVariable Integer id) {
        service.setLang(language);
        return service.get(id);
    }

    @PostMapping(value = PATH + "/category/create/")
    public ResponseEntity<Data<Integer>> create( CategoryCreateDTO dto) {
        return service.create(dto);
    }


    @PutMapping (value = PATH + "/category/update")
    public ResponseEntity<Data<Boolean>> update(@RequestHeader("accept-language") String language, @RequestBody CategoryUpdateDTO dto) {
        service.setLang(language);
        return service.update(dto);
    }
    @DeleteMapping(value = PATH + "/category/delete/{id}")
    public ResponseEntity<Data<Void>>delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @DeleteMapping(value = PATH + "/category/all")
    public ResponseEntity<Data<List<CategoryDTO>>> getAll() {
        return service.getAll();
    }



    @GetMapping(PATH+"/download/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + file.getFilename() + "\"").body(file);

    }

}
