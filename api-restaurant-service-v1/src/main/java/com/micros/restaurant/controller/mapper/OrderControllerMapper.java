package com.micros.restaurant.controller.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.micros.restaurant.domain.Customer;
import com.micros.restaurant.domain.Order;
import com.micros.restaurant.domain.OrderItem;
import com.micros.restaurant.domain.Restaurant;
import com.micros.restaurant.dto.OrderItemResponseDTO;
import com.micros.restaurant.dto.ReserveOrderRequestDTO;
import com.micros.restaurant.dto.ReserveOrderResponseDTO;

@Component
public class OrderControllerMapper {

        // DTO (request) -> Dominio
        public Order toDomain(ReserveOrderRequestDTO request) {

                // Creamos un restaurant de dominio
                Restaurant restaurant = new Restaurant(request.restaurantId(), null);

                // Creamos un cliente de dominio
                Customer customer = new Customer(request.customerId());

                // Necesitamos convertir cada orderItemRequestDTO en OrderItem de dominio
                List<OrderItem> items = request.items().stream()
                                .map(i -> new OrderItem(
                                                i.productId(),
                                                i.productName(),
                                                i.quantity(),
                                                i.description()))
                                .toList();

                // Construimos y retornamos la orden completa de dominio
                return new Order(request.orderId(), restaurant, customer, items);

        }

        // Dominio -> DTO (response)
        public ReserveOrderResponseDTO toResponse(Order order) {

                // Convertir cada OrderItem del dominio en OrderItemResponse
                List<OrderItemResponseDTO> items = order.getItems().stream()
                                .map(i -> new OrderItemResponseDTO(
                                                i.getProductId(),
                                                i.getProductName(),
                                                i.getQuantity(),
                                                i.getDescription()))
                                .toList();

                // Construimos y retornamos la respuesta correcta
                return new ReserveOrderResponseDTO(
                                order.getOrderId(),
                                order.getRestaurant().getId(),
                                order.getCustomer().getCustomerId(),
                                items);
        }

}
