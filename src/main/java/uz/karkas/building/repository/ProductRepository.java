package uz.karkas.building.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.karkas.building.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, AbstractRepository {



}
