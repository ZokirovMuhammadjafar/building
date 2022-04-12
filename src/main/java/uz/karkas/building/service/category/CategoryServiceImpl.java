package uz.karkas.building.service.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.karkas.building.domain.Category;
import uz.karkas.building.domain.Product;
import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryDTO;
import uz.karkas.building.dto.category.CategoryUpdateDTO;
import uz.karkas.building.exception.NotFoundException;
import uz.karkas.building.repository.CategoryRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.validator.category.CategoryValidator;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends AbstractService<CategoryRepository, CategoryValidator> implements CategoryService {

    private static String language = "ru";

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
    public ResponseEntity<Data<Boolean>> update(CategoryUpdateDTO updateDTO) {
        Optional<Category> byId = repository.findById(updateDTO.getId());
        if (Objects.isNull(byId)){
            throw new NotFoundException("NOT_FOUND_EXCEPTION");
        }

            Boolean response;
            if (language.equals("uz")) {
                response = repository.updateUZ(updateDTO);
            } else {
                response = repository.updateRU(updateDTO);
            }
            return new ResponseEntity<>(new Data<>(response), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        repository.findById(id).orElseThrow(RuntimeException::new);
        repository.deleteById(id);
        return new ResponseEntity<>(new Data<>(null), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Data<CategoryDTO>> get(Integer id) {
        Category category = repository.findById(id).orElseThrow(RuntimeException::new);
        CategoryDTO categoryDTO = category.get(language);
        return new ResponseEntity<>(new Data<>(categoryDTO), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Data<List<CategoryDTO>>> getAll() {
        return null;
    }

    public void setLang(String lang) {
        if (lang.equalsIgnoreCase("uz")) {
            language = lang;
        } else {
            language = "ru";
        }
    }


}
