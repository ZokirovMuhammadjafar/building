package uz.karkas.building.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import uz.karkas.building.service.base.BaseGenericService;
import uz.karkas.building.service.base.FileService;

@Getter
@RequiredArgsConstructor
public abstract class BaseController<D extends BaseGenericService> {

    protected final D service;




    protected final String PATH="/api/v1";


}
