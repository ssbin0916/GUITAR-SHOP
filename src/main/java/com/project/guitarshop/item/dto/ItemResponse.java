package com.project.guitarshop.item.dto;

import com.project.guitarshop.item.entity.Brand;
import com.project.guitarshop.item.entity.Category;
import com.project.guitarshop.item.entity.Item;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

public class ItemResponse {

    @Getter
    public static class AddItemResponse {
        private final Long id;
        private final String name;
        private final int price;
        private final int quantity;
        private final Category category;
        private final Brand brand;

        @QueryProjection
        public AddItemResponse(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
            this.quantity = item.getQuantity();
            this.category = item.getCategory();
            this.brand = item.getBrand();
        }
    }

    @Getter
    public static class FindItemResponse {
        private final String name;
        private final Integer price;
        private final Category category;
        private final Brand brand;

        @QueryProjection
        public FindItemResponse(String name, int price, Category category, Brand brand) {
            this.name = name;
            this.price = price;
            this.category = category;
            this.brand = brand;
        }
    }

    @Getter
    public static class CancelItemResponse {
        private final Long id;
        private final String name;
        private final Integer price;

        public CancelItemResponse(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
        }
    }
}
