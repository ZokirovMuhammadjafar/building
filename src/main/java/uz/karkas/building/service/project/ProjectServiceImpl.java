package uz.karkas.building.service.project;

import org.springframework.http.ResponseEntity;
import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;
import uz.karkas.building.repository.ProjectRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.validator.project.ProjectValidator;

import java.util.List;

public class ProjectServiceImpl extends AbstractService<ProjectRepository, ProjectValidator> implements ProjectService{

    protected ProjectServiceImpl(ProjectValidator validator, ProjectRepository repository) {
        super(validator, repository);
    }

    @Override
    public ResponseEntity<Data<Integer>> create(ProjectCreateDTO createDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(ProjectUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<ProjectDTO>> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<ProjectDTO>>> getAll() {
        return null;
    }
}
