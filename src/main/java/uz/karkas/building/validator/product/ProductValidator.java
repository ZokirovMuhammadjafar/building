package uz.karkas.building.validator.product;

import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductUpdateDTO;
import uz.karkas.building.exception.validator.ValidationException;
import uz.karkas.building.validator.base.AbstractValidator;

public class ProductValidator extends AbstractValidator<ProductCreateDTO, ProductUpdateDTO, Integer> {
    @Override
    public void validateKey(Integer id) throws ValidationException {

    }

    @Override
    public void validOnCreate(ProductCreateDTO createDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(ProductUpdateDTO updateDto) throws ValidationException {

    }
}
