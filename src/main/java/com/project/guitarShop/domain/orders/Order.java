package com.project.guitarShop.domain.orders;

import com.project.guitarShop.domain.orderItem.OrderItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Order {

    private Long id;

    @NotNull
    private Long memberId;
    @Min(value = 0)
    private Integer totalPrice;
    @NotNull
    private String address;

    private LocalDateTime orderDate = LocalDateTime.now();

    private List<OrderItem> orderItems = new ArrayList<>();

    private void saveOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
}