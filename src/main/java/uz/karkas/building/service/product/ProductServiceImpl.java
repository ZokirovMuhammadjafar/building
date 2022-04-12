package uz.karkas.building.service.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.karkas.building.domain.Product;
import uz.karkas.building.domain.Uploads;
import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductDTO;
import uz.karkas.building.dto.product.ProductUpdateDTO;
import uz.karkas.building.repository.ProductRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.service.base.FileService;
import uz.karkas.building.validator.product.ProductValidator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends AbstractService<ProductRepository, ProductValidator> implements ProductService {

    private final FileService service;
    private static String language = "ru";
    @Value("${path.request}")
    private String request;
    @Value("${path.api}")
    private String api;
    @Value("${path.urlPath}")
    private String urlPath;

    protected ProductServiceImpl(ProductValidator validator, ProductRepository repository, FileService service) {
        super(validator, repository);
        this.service = service;

    }

    @Override
    public ResponseEntity<Data<Integer>> create(ProductCreateDTO createDTO) {
        Integer pictureId = null;
        try {
            pictureId = service.save(createDTO.getPhoto());
        } catch (IOException e) {
            // TODO: 10/04/2022 appp error dto
            e.printStackTrace();
        }
        Product product = Product.create(createDTO, pictureId);
        Product save = repository.save(product);
        return new ResponseEntity<>(new Data<>(save.getId()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(ProductUpdateDTO updateDTO) {
        Boolean response;
        if (language.equals("uz")) {
            response = repository.updateUZ(updateDTO);
        } else {
            response = repository.updateRU(updateDTO);
        }
        return new ResponseEntity<>(new Data<>(response), HttpStatus.OK);
    }

    public void setLang(String lang) {
        if (lang.equalsIgnoreCase("uz")) {
            language = lang;
        } else {
            language = "ru";
        }
    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        repository.findById(id).orElseThrow(RuntimeException::new/*()->{throw new NotFoundException("product not found");
        }*/);
        repository.deleteById(id);
        return new ResponseEntity<>(new Data<>(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<ProductDTO>> get(Integer id) {
        Product product = repository.findById(id).orElseThrow(RuntimeException::new);
        ProductDTO productDTO = product.get(language);
        Uploads uploads = service.get(product.getFileId());

        productDTO.setUrl(request.concat(api).concat(urlPath).concat("download/").concat(uploads.getPathName()));
        return new ResponseEntity<>(new Data<>(productDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<List<ProductDTO>>> getAll() {
        String concat = request.concat(api).concat(urlPath).concat("download/");
        List<Product> all = repository.findAll();
        List<ProductDTO> products = all.stream().map(a -> {
            ProductDTO ru = a.get("ru");
            Uploads uploads = service.get(a.getFileId());
            ru.setUrl(concat.concat(uploads.getPathName()));
            return ru;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(new Data<>(products),HttpStatus.OK);
    }
}
