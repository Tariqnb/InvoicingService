package edu.iu.c322.InvoicingService.repository;


import edu.iu.c322.InvoicingService.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public List<Order> findAll() {
        return orders;
    }

    public long create(Order order) {
        orders.add(order);
        return order.getCustomerId();
    }

    public void update(Order order, int id) {
        Order existingOrder = getOrderById((long) id);
        if (existingOrder != null) {
            existingOrder.setTotal(order.getTotal());
            existingOrder.setShippingAddress(order.getShippingAddress());
            existingOrder.setItems(order.getItems());
            existingOrder.setPayment(order.getPayment());
        } else {
            throw new IllegalStateException("Order ID is not valid");
        }
    }

    public void delete(int id) {
        Order existingOrder = getOrderById((long)id);
        if (existingOrder != null) {
            orders.remove(existingOrder);
        } else {
            throw new IllegalStateException("Order ID is not valid");
        }
    }

    public Optional<Order> findById(Long id) {
        Order existingOrder = getOrderById(id);
        return Optional.ofNullable(existingOrder);
    }

    public Order getOrderById(Long id) {
        return orders.stream()
                .filter(order -> order.getCustomerId().equals(id))
                .findFirst()
                .orElse(null);

    }
}




