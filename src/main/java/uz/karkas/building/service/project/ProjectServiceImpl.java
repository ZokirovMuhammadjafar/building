package uz.karkas.building.service.project;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.karkas.building.controller.swagger.ApiProperties;
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

import javax.transaction.Transactional;
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
        Project project = Project.create(createDTO);
        Project save = repository.save(project);
        return new ResponseEntity<>(new Data<>(save.getId()), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Data<Boolean>> update(ProjectUpdateDTO updateDTO, String language) {

        boolean ans;
        if (language.equals("uz")) {
            repository.updateUZ(updateDTO.getDescription(), updateDTO.getTitle(), updateDTO.getId());
        } else {
            repository.updateRU(updateDTO.getDescription(), updateDTO.getTitle(), updateDTO.getId());
        }

        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);

    }

    @Override
    public ResponseEntity.HeadersBuilder<?> delete(Integer id) {
        Optional<Project> get = repository.findById(id);
        Project project = get.orElseThrow(RuntimeException::new);
        repository.deleteById(id);
        return ResponseEntity.noContent();


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
        List<Project> all = repository.findAllByOrderByIdDesc(Pageable.ofSize(10));
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
