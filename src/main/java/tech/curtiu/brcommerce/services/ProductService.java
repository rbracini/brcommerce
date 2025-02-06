package tech.curtiu.brcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return new ProductDTO(product);
    }

}
