package tech.curtiu.brcommerce.mappers;

import org.mapstruct.Mapper;

import tech.curtiu.brcommerce.dto.CategoryDTO;
import tech.curtiu.brcommerce.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
}
