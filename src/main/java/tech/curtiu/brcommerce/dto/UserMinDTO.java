package tech.curtiu.brcommerce.dto;

import tech.curtiu.brcommerce.entities.User;

public class UserMinDTO {

    private Long id;
    private String email;
    private String name;

    public UserMinDTO(User entity) {
        id = entity.getId();
        email = entity.getEmail();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
