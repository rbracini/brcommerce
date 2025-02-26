package tech.curtiu.brcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import tech.curtiu.brcommerce.dto.ProductDTO;
import tech.curtiu.brcommerce.entities.Product;
import tech.curtiu.brcommerce.mappers.ProductMapper;
import tech.curtiu.brcommerce.repositories.ProductRepository;
import tech.curtiu.brcommerce.services.exceptions.DataIntegrityViolationException;
import tech.curtiu.brcommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductDTO> searchByName(Pageable pageable, String name) {
        Page<Product> products = productRepository.searchByName(pageable, name);
        return products.map(productMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO save(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = productRepository.getReferenceById(id);
            productMapper.updateEntityFromDto(dto, product);
            product = productRepository.save(product);
            return productMapper.toDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }

}
