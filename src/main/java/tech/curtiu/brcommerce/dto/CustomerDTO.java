package tech.curtiu.brcommerce.dto;

import tech.curtiu.brcommerce.entities.User;

public class CustomerDTO {

    private Long id;
    private String name;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}