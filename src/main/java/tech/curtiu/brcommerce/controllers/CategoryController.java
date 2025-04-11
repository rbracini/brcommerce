package tech.curtiu.brcommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.curtiu.brcommerce.dto.CategoryDTO;
import tech.curtiu.brcommerce.services.CategoryServices;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok(categoryServices.findAll());
    }

}
