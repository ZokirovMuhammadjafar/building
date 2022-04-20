package uz.karkas.building.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductDTO;
import uz.karkas.building.dto.product.ProductUpdateDTO;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.service.product.ProductServiceImpl;

import java.util.List;

@RestController
public class ProductController extends BaseController<ProductServiceImpl> {

    private final FileService fileService;

    public ProductController(ProductServiceImpl service, FileService fileService) {
        super(service);
        this.fileService=fileService;
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
    public ResponseEntity<Data<Void>>delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @DeleteMapping(value = PATH + "/product/all")
    public ResponseEntity<Data<List<ProductDTO>>>getAll(@RequestHeader("accept-language") String language) {
        return service.getAll(language);
    }




    @GetMapping(PATH+"/download/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + file.getFilename() + "\"").body(file);

    }


}
