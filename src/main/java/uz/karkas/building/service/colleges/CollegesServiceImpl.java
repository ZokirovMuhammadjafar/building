package uz.karkas.building.service.colleges;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.karkas.building.config.swagger.ApiProperties;
import uz.karkas.building.domain.Colleges;
import uz.karkas.building.domain.Uploads;
import uz.karkas.building.dto.colleges.CollegesCreateDTO;
import uz.karkas.building.dto.colleges.CollegesDTO;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.repository.CollegesRepository;
import uz.karkas.building.response.ApiErrorDto;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.validator.colleges.CollegesValidator;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CollegesServiceImpl extends AbstractService<CollegesRepository, CollegesValidator> implements CollegesService {
    private final FileService service;
    private final ApiProperties properties;

    protected CollegesServiceImpl(CollegesValidator validator, CollegesRepository repository, FileService service,
                                  ApiProperties properties) {
        super(validator, repository);
        this.service = service;
        this.properties = properties;
    }

    @Override
    @Transactional
    public ResponseEntity<Data<Integer>> create(CollegesCreateDTO createDTO) {
        try {
            Integer save = service.save(createDTO.getPicture());
            Colleges colleges = new Colleges();
            colleges.setDescriptionRU(createDTO.getDescriptionRU());
            colleges.setDescriptionUZ(createDTO.getDescriptionUZ());
            colleges.setFileId(save);
            colleges.setNameRU(createDTO.getNameRU());
            colleges.setNameUZ(createDTO.getNameUZ());
            Integer id = repository.save(colleges).getId();
            return new ResponseEntity<>(new Data<>(id), HttpStatus.OK);
        } catch (IOException e) {
            ApiErrorDto dto = ApiErrorDto.builder().status(HttpStatus.NOT_ACCEPTABLE.value()).message("not create").developerMessage(e.getMessage()).build();
            return new ResponseEntity<>(new Data<>(dto),HttpStatus.OK);
        }

    }

    @Transactional
    @Override
    public ResponseEntity<Data<Boolean>> update(CollegesUpdateDTO updateDTO,String lang) {
        boolean ans;
        if (lang.equals("uz")) {
            ans = repository.updateUZ(updateDTO);
        } else {
            ans = repository.updateRU(updateDTO);
        }

        return new ResponseEntity<>(new Data<>(ans), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        Optional<Colleges> get = repository.findById(id);
        if (get.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new Data<>(null), HttpStatus.OK);
        } else {
            ApiErrorDto errorDto = ApiErrorDto.builder().status(HttpStatus.NOT_FOUND.value()).message("colleges not found").build();
            return new ResponseEntity<>(new Data<>(errorDto), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Data<CollegesDTO>> get(Integer id, String language) {
        Optional<Colleges> get = repository.findById(id);
        if (get.isPresent()) {
            Colleges colleges = get.get();
            Uploads uploads = service.get(colleges.getFileId());
            CollegesDTO collegesDTO = colleges.get(language);
            collegesDTO.setUrl(properties.getRequest().concat(properties.getApi()).concat(properties.getUrlPath()).concat("download/").concat(uploads.getPathName()));
            return new ResponseEntity<>(new Data<>(collegesDTO), HttpStatus.OK);
        } else {

            ApiErrorDto errorDto = ApiErrorDto.builder().status(HttpStatus.NOT_FOUND.value()).message("colleges not found").build();
            return new ResponseEntity<>(new Data<>(errorDto), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Data<List<CollegesDTO>>> getAll(String language) {
        List<Colleges> all = repository.findAll();
        String concat = properties.getRequest().concat(properties.getApi()).concat(properties.getUrlPath()).concat("download/");
        List<CollegesDTO> colleges = all.stream().map(a -> {
            CollegesDTO ru = a.get(language);
            Uploads uploads = service.get(a.getFileId());
            ru.setUrl(concat.concat(uploads.getPathName()));
            return ru;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(new Data<>(colleges),HttpStatus.OK);
    }
}
