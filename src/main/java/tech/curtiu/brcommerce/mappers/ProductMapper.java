package tech.curtiu.brcommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import tech.curtiu.brcommerce.dto.ProductDTO;
import tech.curtiu.brcommerce.dto.ProductMinDTO;
import tech.curtiu.brcommerce.entities.Product;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    ProductMinDTO toMinDTO(Product product);

    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ProductDTO dto, @MappingTarget Product product);

}
