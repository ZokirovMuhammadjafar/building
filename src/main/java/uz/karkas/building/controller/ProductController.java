package uz.karkas.building.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductDTO;
import uz.karkas.building.dto.product.ProductUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.product.ProductService;
import uz.karkas.building.service.product.ProductServiceImpl;

import java.util.List;

@RestController

public class ProductController extends BaseController<ProductServiceImpl> {


    public ProductController(ProductServiceImpl service) {
        super(service);
    }

    @GetMapping(value = PATH + "/product/{id}")
    public ResponseEntity<Data<ProductDTO>> get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping(value = PATH + "/product/create/")
    public ResponseEntity<Data<Integer>> create(@RequestBody ProductCreateDTO dto) {
        return service.create(dto);
    }


    @PutMapping (value = PATH + "/product/update")
    public ResponseEntity<Data<Boolean>> update(@RequestBody ProductUpdateDTO dto) {
        return service.update(dto);
    }
    @DeleteMapping(value = PATH + "/product/delete/{id}")
    public ResponseEntity<Data<Void>>delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @DeleteMapping(value = PATH + "/product/all")
    public ResponseEntity<Data<List<ProductDTO>>>getAll() {
        return service.getAll();
    }




}
