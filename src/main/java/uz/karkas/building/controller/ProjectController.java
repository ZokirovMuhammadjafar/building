package uz.karkas.building.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.service.project.ProjectServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
public class ProjectController extends BaseController<ProjectServiceImpl> {

    private final FileService fileService;

    public ProjectController(ProjectServiceImpl service, FileService fileService) {
        super(service);
        this.fileService = fileService;
    }


    @GetMapping(PATH + "/project/get/{id}")
    public ResponseEntity<Data<ProjectDTO>> get(@PathVariable Integer id, @RequestHeader("accept-language") String language) {
        return service.get(id, language);
    }


    @PostMapping(PATH + "/project/create")
    public ResponseEntity<Data<Integer>> create(@RequestBody @Valid ProjectCreateDTO dto) {
        return service.create(dto);
    }


    @PutMapping(PATH + "/project/update")
    public ResponseEntity<Data<Boolean>> update(@RequestBody @Valid ProjectUpdateDTO dto, @RequestHeader("accept-language") String language) {
        return service.update(dto, language);

    }


    @DeleteMapping(PATH + "/project/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @GetMapping(PATH + "/project/all")
    public ResponseEntity<Data<List<ProjectDTO>>> getAll(@RequestHeader("accept-language") String language) {
        return service.getAll(language);
    }


}
