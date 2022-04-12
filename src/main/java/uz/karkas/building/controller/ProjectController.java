package uz.karkas.building.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.project.ProjectServiceImpl;

import javax.validation.Valid;
import java.util.List;

public class ProjectController extends BaseController<ProjectServiceImpl> {

    public ProjectController(ProjectServiceImpl service) {
        super(service);
    }

    @GetMapping(PATH + "/project/{id}")
    public ResponseEntity<Data<ProjectDTO>> get(@PathVariable Integer id, @RequestHeader("accept-language") String language) {
        return service.get(id, language);
    }

    @PostMapping(PATH + "/project/create}")
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
