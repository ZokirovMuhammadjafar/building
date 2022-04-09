package uz.karkas.building.service.category;

import org.springframework.http.ResponseEntity;
import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryDTO;
import uz.karkas.building.dto.category.CategoryUpdateDTO;
import uz.karkas.building.repository.CategoryRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.validator.category.CategoryValidator;

import java.util.List;

public class CategoryServiceImpl extends AbstractService<CategoryRepository, CategoryValidator> implements CategoryService {

    protected CategoryServiceImpl(CategoryValidator validator, CategoryRepository repository) {
        super(validator, repository);
    }

    @Override
    public ResponseEntity<Data<Integer>> create(CategoryCreateDTO createDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(CategoryUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<CategoryDTO>> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<CategoryDTO>>> getAll() {
        return null;
    }
}
