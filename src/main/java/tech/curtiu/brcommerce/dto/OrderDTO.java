package tech.curtiu.brcommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import tech.curtiu.brcommerce.entities.Order;
import tech.curtiu.brcommerce.entities.OrderItem;
import tech.curtiu.brcommerce.entities.OrderStatus;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private CustomerDTO customer;
    private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo menos um item")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, CustomerDTO customer, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.customer = customer;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        customer = new CustomerDTO(entity.getCustomer());
        status = entity.getStatus();
        if (entity.getPayment() != null) {
            payment = new PaymentDTO(entity.getPayment());
        }
        for (OrderItem item : entity.getItems()) {
            items.add(new OrderItemDTO(item));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItemDTO item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

    public void addItem(OrderItemDTO item) {
        items.add(item);
    }

    public void removeItem(OrderItemDTO item) {
        items.remove(item);
    }

    public void clearItems() {
        items.clear();
    }

}
