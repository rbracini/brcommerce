package tech.curtiu.brcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.curtiu.brcommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}