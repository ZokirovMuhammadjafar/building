package uz.karkas.building.validator.category;

import org.springframework.stereotype.Component;
import uz.karkas.building.dto.category.CategoryCreateDTO;
import uz.karkas.building.dto.category.CategoryUpdateDTO;
import uz.karkas.building.exception.validator.ValidationException;
import uz.karkas.building.validator.base.AbstractValidator;

@Component
public class CategoryValidator extends AbstractValidator<CategoryCreateDTO, CategoryUpdateDTO, Integer> {
    @Override
    public void validateKey(Integer id) throws ValidationException {

    }

    @Override
    public void validOnCreate(CategoryCreateDTO createDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(CategoryUpdateDTO updateDto) throws ValidationException {

    }
}
