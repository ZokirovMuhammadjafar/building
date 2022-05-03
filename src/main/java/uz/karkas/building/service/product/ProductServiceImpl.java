package uz.karkas.building.service.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
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

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends AbstractService<ProductRepository, ProductValidator> implements ProductService {

    private final FileService service;
    @Value("${path.request}")
    private String request;
    @Value("${path.api}")
    private String api;
    @Value("${path.url-path}")
    private String urlPath;

    protected ProductServiceImpl(ProductValidator validator, ProductRepository repository, FileService service) {
        super(validator, repository);
        this.service = service;

    }


    @Override
    public ResponseEntity<Data<Integer>> create(ProductCreateDTO createDTO) {
        Integer pictureId = service.get(createDTO.getPictureId()).getId();
        Product product = Product.create(createDTO, pictureId);
        Product save = repository.save(product);
        return new ResponseEntity<>(new Data<>(save.getId()), HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<Data<Boolean>> update(ProductUpdateDTO updateDTO,String language) {
        Boolean response;
        if (language.equals("uz")) {
             repository.updateUZ(updateDTO);
        } else {
            repository.updateRU(updateDTO);
        }
        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }





    @Override
    public ResponseEntity.HeadersBuilder<?> delete(Integer id) {
        repository.findById(id).orElseThrow(RuntimeException::new/*()->{throw new NotFoundException("product not found");
        }*/);
        repository.deleteById(id);
        return ResponseEntity.noContent();
    }


    @Override
    public ResponseEntity<Data<ProductDTO>> get(Integer id,String language) {
        Product product = repository.findById(id).orElseThrow(RuntimeException::new);
        ProductDTO productDTO = product.get(language);
        Uploads uploads = service.get(product.getFileId());

        productDTO.setUrl(request.concat(api).concat(urlPath).concat("download/").concat(uploads.getPathName()));
        return new ResponseEntity<>(new Data<>(productDTO), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Data<List<ProductDTO>>> getAll(String language) {
        String concat = request.concat(api).concat(urlPath).concat("download/");
        List<Product> all = repository.findAllByOrderByIdDesc(Pageable.ofSize(10));
        List<ProductDTO> products = all.stream().map(a -> {
            ProductDTO ru = a.get(language);
            Uploads uploads = service.get(a.getFileId());
            ru.setUrl(concat.concat(uploads.getPathName()));
            return ru;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(new Data<>(products),HttpStatus.OK);
    }
}
