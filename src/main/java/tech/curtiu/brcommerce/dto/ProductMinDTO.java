package tech.curtiu.brcommerce.dto;

import tech.curtiu.brcommerce.entities.Product;

public class ProductMinDTO {

    private Long id;
    private String name;
    private Double price;

    public ProductMinDTO() {
    }

    public ProductMinDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

}
