package tech.curtiu.brcommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.curtiu.brcommerce.dto.UserMinDTO;
import tech.curtiu.brcommerce.entities.User;
import tech.curtiu.brcommerce.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserMinDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMinDTO::new)
                .toList();
    }

}
