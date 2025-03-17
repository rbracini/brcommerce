package tech.curtiu.brcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.curtiu.brcommerce.dto.UserDTO;
import tech.curtiu.brcommerce.dto.UserMinDTO;
import tech.curtiu.brcommerce.entities.Role;
import tech.curtiu.brcommerce.entities.User;
import tech.curtiu.brcommerce.projections.UserDetailsProjection;
import tech.curtiu.brcommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<UserMinDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMinDTO::new)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> listUsers = userRepository.searchUserAndRolesByEmail(username);
        if (listUsers.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User();
        user.setEmail(username);
        user.setPassword(listUsers.get(0).getPassword());
        listUsers.forEach(x -> user.addRole(new Role(x.getRoleId(), x.getAuthority())));

        return user;
    }

    protected User authenticated() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwtPrincipal) {
            String username = jwtPrincipal.getClaim("username");
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        } else {
            throw new UsernameNotFoundException(
                    "Invalid authentication principal type: " + authentication.getPrincipal().getClass().getName());
        }

    }

    @Transactional(readOnly = true)
    public UserDTO authenticatedUser() {
        return new UserDTO(authenticated());
    }

}
