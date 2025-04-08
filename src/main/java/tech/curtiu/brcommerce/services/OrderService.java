package tech.curtiu.brcommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.curtiu.brcommerce.dto.OrderDTO;
import tech.curtiu.brcommerce.dto.OrderItemDTO;
import tech.curtiu.brcommerce.entities.Order;
import tech.curtiu.brcommerce.entities.OrderItem;
import tech.curtiu.brcommerce.entities.OrderStatus;
import tech.curtiu.brcommerce.entities.Product;
import tech.curtiu.brcommerce.repositories.OrderRepository;
import tech.curtiu.brcommerce.repositories.ProductRepository;
import tech.curtiu.brcommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO save(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setCustomer(userService.authenticated());
        dto.getItems().forEach(item -> order.addItem(createOrderItem(order, item)));

        return new OrderDTO(orderRepository.save(order));
    }

    private OrderItem createOrderItem(Order order, OrderItemDTO itemDTO) {
        Product product = productRepository.getReferenceById(itemDTO.getProductId());
        return new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
    }

}
