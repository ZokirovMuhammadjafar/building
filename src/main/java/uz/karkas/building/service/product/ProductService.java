package uz.karkas.building.service.product;

import uz.karkas.building.dto.product.ProductCreateDTO;
import uz.karkas.building.dto.product.ProductDTO;
import uz.karkas.building.dto.product.ProductUpdateDTO;
import uz.karkas.building.service.base.GenericCRUDService;
import uz.karkas.building.service.base.GenericService;

public interface ProductService extends GenericCRUDService<ProductDTO, ProductCreateDTO, ProductUpdateDTO, Integer> {
}
