package uz.karkas.building.validator.base;

import uz.karkas.building.exception.validator.ValidationException;

public abstract class AbstractValidator<CD, UD, K> implements BaseGenericValidator {

    public abstract void validateKey(K id) throws ValidationException;

    public abstract void validOnCreate(CD createDto) throws ValidationException;

    public abstract void validOnUpdate(UD updateDto) throws ValidationException;

    public boolean isPositive(Double number) {
        return number > 0;
    }

    public boolean isPositiveOrZero(Double number) {
        return number >= 0;
    }

    public boolean isNegative(Double number) {
        return number < 0;
    }

    public boolean isNegativeOrZero(Double number) {
        return number <= 0;
    }

}
