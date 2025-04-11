package tech.curtiu.brcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.curtiu.brcommerce.dto.CategoryDTO;
import tech.curtiu.brcommerce.repositories.CategoryRepository;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
    }

}
