package tech.curtiu.brcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.curtiu.brcommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
