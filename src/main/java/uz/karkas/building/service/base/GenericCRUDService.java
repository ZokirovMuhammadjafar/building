package uz.karkas.building.service.base;

import org.springframework.http.ResponseEntity;
import uz.karkas.building.dto.base.DTO;
import uz.karkas.building.response.Data;

import java.io.Serializable;

public interface GenericCRUDService<
        D extends DTO,
        CD extends DTO,
        UD extends DTO,
        K extends Serializable
        > extends GenericService<D, K> {


    ResponseEntity<Data<K>> create(CD createDTO);

    ResponseEntity<Data<Boolean>> update(UD updateDTO);

    ResponseEntity<Data<Void>> delete(K id);
}
