package com.project.guitarShop.dto.item;

import com.project.guitarShop.item.api.ItemRequestDto;
import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import lombok.Builder;


public record ItemRequest(String name, int price, int quantity, Category category, Brand brand) {

    @Builder
    public ItemRequest {

    }

    public static ItemRequest toRequest(ItemRequestDto itemRequestDTO) {
        return ItemRequest.builder()
                .name(itemRequestDTO.name())
                .price(itemRequestDTO.price())
                .quantity(itemRequestDTO.quantity())
                .category(itemRequestDTO.category())
                .brand(itemRequestDTO.brand())
                .build();
    }
}