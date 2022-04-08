package uz.karkas.building.validator.contact;

import uz.karkas.building.dto.contact.ContactCreateDTO;
import uz.karkas.building.dto.contact.ContactUpdateDTO;
import uz.karkas.building.exception.validator.ValidationException;
import uz.karkas.building.validator.base.AbstractValidator;

public class ContactValidator extends AbstractValidator<ContactCreateDTO, ContactUpdateDTO, Integer> {
    @Override
    public void validateKey(Integer id) throws ValidationException {

    }

    @Override
    public void validOnCreate(ContactCreateDTO createDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(ContactUpdateDTO updateDto) throws ValidationException {

    }
}
