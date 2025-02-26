package tech.curtiu.brcommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.curtiu.brcommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Usando Query Methods
    Page<Product> findByNameContainingIgnoreCase(Pageable pageable, String name);

    // Usando JPQL
    @Query("SELECT prod FROM Product prod WHERE UPPER(prod.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(Pageable pageable, String name);

}
