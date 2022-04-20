package uz.karkas.building.service.project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.karkas.building.config.swagger.ApiProperties;
import uz.karkas.building.domain.Project;
import uz.karkas.building.domain.Uploads;
import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;
import uz.karkas.building.repository.ProjectRepository;
import uz.karkas.building.response.ApiErrorDto;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.validator.project.ProjectValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractService<ProjectRepository, ProjectValidator> implements ProjectService {

    private final FileService service;
    private final ApiProperties properties;

    protected ProjectServiceImpl(ProjectValidator validator, ProjectRepository repository, FileService service, ApiProperties properties) {
        super(validator, repository);
        this.service = service;
        this.properties = properties;
    }

    @Override
    public ResponseEntity<Data<Integer>> create(ProjectCreateDTO createDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(ProjectUpdateDTO updateDTO, String language) {

        boolean ans;
        if (language.equals("uz")) {
            ans = repository.updateUZ(updateDTO);
        } else {
            ans = repository.updateRU(updateDTO);
        }

        return new ResponseEntity<>(new Data<>(ans), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        Optional<Project> get = repository.findById(id);
        if (get.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new Data<>(null), HttpStatus.OK);
        } else {
            ApiErrorDto errorDto = ApiErrorDto.builder().status(HttpStatus.NOT_FOUND.value()).message("project not found").build();
            return new ResponseEntity<>(new Data<>(errorDto), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Data<ProjectDTO>> get(Integer id, String language) {
        Optional<Project> get = repository.findById(id);
        if (get.isPresent()) {
            Project project = get.get();
            Uploads uploads = service.get(project.getFileId());
            ProjectDTO projectDTO = project.get(language);
            projectDTO.setUrl(properties.getRequest().concat(properties.getApi()).concat(properties.getUrlPath()).concat("download/").concat(uploads.getPathName()));
            return new ResponseEntity<>(new Data<>(projectDTO), HttpStatus.OK);
        } else {

            ApiErrorDto errorDto = ApiErrorDto.builder().status(HttpStatus.NOT_FOUND.value()).message("project not found").build();
            return new ResponseEntity<>(new Data<>(errorDto), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Data<List<ProjectDTO>>> getAll(String language) {
        List<Project> all = repository.findAll();
        String concat = properties.getRequest().concat(properties.getApi()).concat(properties.getUrlPath()).concat("download/");
        List<ProjectDTO> projectDTO = all.stream().map(a -> {
            ProjectDTO ru = a.get(language);
            Uploads uploads = service.get(a.getFileId());
            ru.setUrl(concat.concat(uploads.getPathName()));
            return ru;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(new Data<>(projectDTO), HttpStatus.OK);
    }

}
