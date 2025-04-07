package tech.curtiu.brcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.curtiu.brcommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}