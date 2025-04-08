package tech.curtiu.brcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.curtiu.brcommerce.entities.User;
import tech.curtiu.brcommerce.enums.RoleEnum;
import tech.curtiu.brcommerce.services.exceptions.ForbiddenException;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();
        if (me == null || (!me.hasRole(RoleEnum.ADMIN.getValue()) && !me.getId().equals(userId))) {
            throw new ForbiddenException("Acesso negado");
        }
    }
}
