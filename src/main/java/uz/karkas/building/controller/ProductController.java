package uz.karkas.building.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductDTO;
import uz.karkas.building.dto.product.ProductUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.service.product.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController extends BaseController<ProductServiceImpl> {



    public ProductController(ProductServiceImpl service) {
        super(service);

    }

    @GetMapping(value = PATH + "/product/get/{id}")
    public ResponseEntity<Data<ProductDTO>> get(@RequestHeader("accept-language") String language, @PathVariable Integer id) {

        return service.get(id,language);
    }

    @PostMapping(value = PATH + "/product/create/")
    public ResponseEntity<Data<Integer>> create(ProductCreateDTO dto) {
        return service.create(dto);
    }


    @PutMapping (value = PATH + "/product/update")
    public ResponseEntity<Data<Boolean>> update(@RequestHeader("accept-language") String language,@RequestBody ProductUpdateDTO dto) {

        return service.update(dto,language);
    }
    @DeleteMapping(value = PATH + "/product/delete/{id}")
    public ResponseEntity.HeadersBuilder<?> delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping(value = PATH + "/product/all")
    public ResponseEntity<Data<List<ProductDTO>>>getAll(@RequestHeader("accept-language") String language, Integer categoryId) {
        return service.getAll(language, categoryId);
    }



}
