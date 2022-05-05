package uz.karkas.building.service.category;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.karkas.building.domain.Category;
import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryDTO;
import uz.karkas.building.dto.category.CategoryUpdateDTO;
import uz.karkas.building.exception.NotFoundException;
import uz.karkas.building.repository.CategoryRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.validator.category.CategoryValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends AbstractService<CategoryRepository, CategoryValidator> implements CategoryService {


    protected CategoryServiceImpl(CategoryValidator validator, CategoryRepository repository) {
        super(validator, repository);
    }

    // todo validator va check service yozish kerak kerak

    @Override
    public ResponseEntity<Data<Integer>> create(CategoryCreateDTO createDTO) {
        Category category = Category.create(createDTO);
        Category save = repository.save(category);
        return new ResponseEntity<>(new Data<>(save.getId()), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Data<Boolean>> update(CategoryUpdateDTO updateDTO, String language) {
        Optional<Category> byId = repository.findById(updateDTO.getId());
        if (Objects.isNull(byId)) {
            throw new NotFoundException("NOT_FOUND_EXCEPTION");
        }

        if (language.equals("uz")) {
            repository.updateUZ(updateDTO.getId(), updateDTO.getName());
        } else {
            repository.updateRU(updateDTO.getId(), updateDTO.getName());
        }
        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }


    @Override
    public ResponseEntity.HeadersBuilder<?> delete(Integer id) {
        repository.findById(id).orElseThrow(RuntimeException::new);
        repository.deleteById(id);
        return ResponseEntity.noContent();
    }


    @Override
    public ResponseEntity<Data<CategoryDTO>> get(Integer id, String language) {
        Category category = repository.findById(id).orElseThrow(RuntimeException::new);
        CategoryDTO categoryDTO = category.get(language);
        return new ResponseEntity<>(new Data<>(categoryDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<List<CategoryDTO>>> getAll(String language) {
        List<Category> category = repository.findAllByOrderByIdDesc(Pageable.ofSize(10));
        List<CategoryDTO> projectDTO = category.stream().map(a -> {
            return a.get(language);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(new Data<>(projectDTO), HttpStatus.OK);
    }


}
