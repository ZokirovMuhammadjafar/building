package uz.karkas.building.service.colleges;

import org.springframework.http.ResponseEntity;
import uz.karkas.building.dto.colleges.CollegesCreateDTO;
import uz.karkas.building.dto.colleges.CollegesDTO;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.repository.CollegesRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.validator.colleges.CollegesValidator;

import java.util.List;

public class CollegesServiceImpl extends AbstractService<CollegesRepository, CollegesValidator> implements CollegesService{
    protected CollegesServiceImpl(CollegesValidator validator, CollegesRepository repository) {
        super(validator, repository);
    }

    @Override
    public ResponseEntity<Data<Integer>> create(CollegesCreateDTO createDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(CollegesUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<CollegesDTO>> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<CollegesDTO>>> getAll() {
        return null;
    }
}
