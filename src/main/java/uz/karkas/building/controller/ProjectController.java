package uz.karkas.building.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.service.project.ProjectServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class ProjectController extends BaseController<ProjectServiceImpl> {

    private final FileService fileService;
    public ProjectController(ProjectServiceImpl service, FileService fileService) {
        super(service);
        this.fileService = fileService;
    }

    @GetMapping(PATH + "/project/{id}")
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
    public ResponseEntity<Data<Void>> delete(@PathVariable Integer id) {

        return service.delete(id);
    }

    @GetMapping(PATH + "/project/getAll")
    public ResponseEntity<Data<List<ProjectDTO>>> getAll(@RequestHeader("accept-language") String language) {
        return service.getAll(language);
    }


}
