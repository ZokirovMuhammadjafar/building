package uz.karkas.building.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.colleges.CollegesCreateDTO;
import uz.karkas.building.dto.colleges.CollegesDTO;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.project.ProjectServiceImpl;

import javax.validation.Valid;
import java.util.List;

public class ProjectController extends BaseController<ProjectServiceImpl>{

    public ProjectController(ProjectServiceImpl service) {
        super(service);
    }

    @GetMapping(PATH + "/project/{id}")
    public ResponseEntity<Data<CollegesDTO>> get(@PathVariable Integer id, @RequestHeader("accept-language") String language) {
        return service.get(id, language);
    }

    @PostMapping(PATH + "/project/create}")
    public ResponseEntity<Data<Integer>> create(@RequestBody @Valid CollegesCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping(PATH + "/project/update")
    public ResponseEntity<Data<Boolean>> update(@RequestBody @Valid CollegesUpdateDTO dto, @RequestHeader("accept-language") String language) {
        dto.setLanguage(language);
        return service.update(dto);

    }

    @DeleteMapping(PATH + "/project/delete/{id}")
    public ResponseEntity<Data<Void>> delete(@PathVariable Integer id) {

        return service.delete(id);
    }

    @GetMapping(PATH+"/project/getAll")
    public ResponseEntity<Data<List<CollegesDTO>>>getAll(@RequestHeader("accept-language") String language){
        return  service.getAll(language);
    }

}
