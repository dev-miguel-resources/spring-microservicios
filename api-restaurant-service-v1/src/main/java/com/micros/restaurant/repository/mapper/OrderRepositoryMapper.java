package com.micros.restaurant.repository.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.micros.restaurant.domain.Customer;
import com.micros.restaurant.domain.Order;
import com.micros.restaurant.domain.OrderItem;
import com.micros.restaurant.domain.Restaurant;
import com.micros.restaurant.infraestructure.entity.OrderEntity;
import com.micros.restaurant.infraestructure.entity.OrderItemEntity;
import com.micros.restaurant.infraestructure.entity.RestaurantEntity;

@Component
public class OrderRepositoryMapper {

    public OrderEntity toEntity(Order order) {

        OrderEntity entity = new OrderEntity();

        // Copiar el ID de la orden del dominio a la entidad
        entity.setId(order.getOrderId());

        // Crear una entidad RestarauntEntity y asignarle su ID
        entity.setRestaurant(new RestaurantEntity());
        entity.getRestaurant().setId(order.getRestaurant().getId());

        // Copiar el ID del cliente
        entity.setCustomerId(order.getCustomer().getCustomerId());

        List<OrderItemEntity> itemEntities = order.getItems().stream().map(i -> {
            OrderItemEntity e = new OrderItemEntity();
            e.setProductId(i.getProductId());
            e.setProductName(i.getProductName());
            e.setQuantity(i.getQuantity());
            e.setDescription(i.getDescription());

            // Asociar cada item con su orden
            e.setOrder(entity);
            return e;
        }).toList();

        // Asignar la lista de items a la orden
        entity.setItems(itemEntities);

        // ESTADO INICIAL: código adicional
        entity.setStatus("RESERVED");

        return entity;
    }

    public Order toDomain(OrderEntity entity) {

        // Necesitar construir el objeto Restaurant a partir de la entidad
        Restaurant restaurant = new Restaurant(entity.getRestaurant().getId(), entity.getRestaurant().getName());

        // Necesitar el objeto customer
        Customer customer = new Customer(entity.getCustomerId());

        // Convertir cada OrderItemEntity a OrderItem del dominio
        List<OrderItem> items = entity.getItems().stream()
                .map(e -> new OrderItem(e.getProductId(), e.getProductName(), e.getQuantity(), e.getDescription()))
                .toList();

        // Retornamos el objeto Order del dominio completo
        return new Order(entity.getId(), restaurant, customer, items);

    }

}
