package tech.curtiu.brcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.curtiu.brcommerce.dto.ProductDTO;
import tech.curtiu.brcommerce.dto.ProductMinDTO;
import tech.curtiu.brcommerce.entities.Product;
import tech.curtiu.brcommerce.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductMinDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMinDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return new ProductDTO(product);
    }

}
