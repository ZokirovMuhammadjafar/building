package uz.karkas.building.service.base;

import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.http.ResponseEntity;
import uz.karkas.building.dto.base.DTO;
import uz.karkas.building.response.Data;

import java.io.Serializable;

public interface GenericService <
        D extends DTO,
        K extends Serializable
        > extends BaseGenericService{

    ResponseEntity<Data<D>> get(K id, String language);
    ResponseEntity<Data<List<D>>> getAll(String language);

}
