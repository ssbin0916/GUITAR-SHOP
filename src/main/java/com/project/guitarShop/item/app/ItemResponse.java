package com.project.guitarShop.item.app;

import com.project.guitarShop.item.domain.Brand;
import com.project.guitarShop.item.domain.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResponse {

    private String name;
    private int price;
    private int quantity;
    private Category category;
    private Brand brand;

    @QueryProjection
    public ItemResponse(String name, int price, int quantity, Category category, Brand brand) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.brand = brand;
    }
}