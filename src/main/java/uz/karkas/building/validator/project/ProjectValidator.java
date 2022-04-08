package uz.karkas.building.validator.project;

import uz.karkas.building.dto.project.ProjectCreateDTO;
import uz.karkas.building.dto.project.ProjectUpdateDTO;
import uz.karkas.building.exception.validator.ValidationException;
import uz.karkas.building.validator.base.AbstractValidator;

public class ProjectValidator extends AbstractValidator<ProjectCreateDTO, ProjectUpdateDTO, Integer> {
    @Override
    public void validateKey(Integer id) throws ValidationException {

    }

    @Override
    public void validOnCreate(ProjectCreateDTO createDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(ProjectUpdateDTO updateDto) throws ValidationException {

    }
}
