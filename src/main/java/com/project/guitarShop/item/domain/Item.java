package com.project.guitarShop.item.domain;

import com.project.guitarShop.item.app.ItemRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Builder
    private Item(String name, int price, int quantity, Category category, Brand brand) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.brand = brand;
    }

    public static Item toDomain(ItemRequest itemRequest) {
        return Item.builder()
                .name(itemRequest.name())
                .price(itemRequest.price())
                .quantity(itemRequest.quantity())
                .category(itemRequest.category())
                .brand(itemRequest.brand())
                .build();
    }

    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if (restStock < 0) {
            throw new IllegalStateException("");
        }
        this.quantity = restStock;
    }


}