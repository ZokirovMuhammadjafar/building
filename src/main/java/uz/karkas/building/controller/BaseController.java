package uz.karkas.building.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uz.karkas.building.service.base.BaseGenericService;

@Getter
@RequiredArgsConstructor
public abstract class BaseController<D extends BaseGenericService> {

    protected final D service;

    private final String api = "/api";

    private final String version="v1";

    protected final String PATH=api+version;

}
