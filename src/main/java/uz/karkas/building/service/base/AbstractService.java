package uz.karkas.building.service.base;

import uz.karkas.building.repository.AbstractRepository;
import uz.karkas.building.validator.base.BaseGenericValidator;

public abstract class AbstractService <
        R extends AbstractRepository,
        V extends BaseGenericValidator
        > implements BaseGenericService {


    protected final V validator;
    protected final R repository;

    protected AbstractService(V validator, R repository) {
        this.validator = validator;
        this.repository = repository;
    }

}
