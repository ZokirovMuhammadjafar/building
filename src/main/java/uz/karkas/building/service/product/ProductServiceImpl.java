package uz.karkas.building.service.product;

import org.springframework.http.ResponseEntity;
import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductDTO;
import uz.karkas.building.dto.product.ProductUpdateDTO;
import uz.karkas.building.repository.ProductRepository;
import uz.karkas.building.response.Data;
import uz.karkas.building.service.base.AbstractService;
import uz.karkas.building.validator.product.ProductValidator;

import java.util.List;

public class ProductServiceImpl extends AbstractService<ProductRepository, ProductValidator> implements ProductService {


    protected ProductServiceImpl(ProductValidator validator, ProductRepository repository) {
        super(validator, repository);
    }

    @Override
    public ResponseEntity<Data<ProductDTO>> create(ProductCreateDTO createDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(ProductUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<ProductDTO>> get(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<ProductDTO>>> getAll() {
        return null;
    }
}
