package com.example.demo.repository;

import com.example.demo.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Order> findByClientId(Long clientId) {
        String sql = "SELECT o FROM Order o WHERE o.client.id = :clientId";
        TypedQuery<Order> query = entityManager.createQuery(sql, Order.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }

    public List<Order> findByDriverId(Long driverId) {
        String sql = "SELECT o FROM Order o WHERE o.driver.id = :driverId";
        TypedQuery<Order> query = entityManager.createQuery(sql, Order.class);
        query.setParameter("driverId", driverId);
        return query.getResultList();
    }

    public Optional<Order> findOrderById(Long id) {
        Order order = entityManager.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    public Optional<Order> updateOrder(Long id, Order order) {
        Optional<Order> existingOrderOptional = findOrderById(id);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();

            if (order.getClient() != null) {
                existingOrder.setClient(order.getClient());
            }
            if (order.getDriver() != null) {
                existingOrder.setDriver(order.getDriver());
            }
            if (order.getDistance() != null) {
                existingOrder.setDistance(order.getDistance());
            }

            Order updatedOrder = entityManager.merge(existingOrder);
            return Optional.of(updatedOrder);
        }
        return Optional.empty();
    }

    public void cancelOrder(Long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order != null) {
            entityManager.remove(order);
        }
    }

    public Order addOrder(Order order) {
        entityManager.persist(order);
        return order;
    }

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o ", Order.class).getResultList();
    }
}
