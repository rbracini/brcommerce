package tech.curtiu.brcommerce.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import tech.curtiu.brcommerce.entities.Product;

public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome: deve ter entre 3 e 80 caracteres")
    @NotBlank(message = "Campo Nome é obrigatório")
    private String name;

    @Size(min = 10, message = "Descrição: deve ter no mínimo 10 caracteres")
    private String description;

    @Positive(message = "Preço: deve ser um valor positivo")
    private Double price;

    private String imgUrl;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
