package uz.karkas.building.validator.colleges;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.karkas.building.dto.colleges.CollegesCreateDTO;
import uz.karkas.building.dto.colleges.CollegesUpdateDTO;
import uz.karkas.building.exception.validator.ValidationException;
import uz.karkas.building.validator.base.AbstractValidator;

@Component
public class CollegesValidator extends AbstractValidator<CollegesCreateDTO, CollegesUpdateDTO, Integer> {
    @Override
    public void validateKey(Integer id) throws ValidationException {

    }

    @Override
    public void validOnCreate(CollegesCreateDTO createDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(CollegesUpdateDTO updateDto) throws ValidationException {

    }
}
