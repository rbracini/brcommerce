package tech.curtiu.brcommerce.repositories;

import tech.curtiu.brcommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
