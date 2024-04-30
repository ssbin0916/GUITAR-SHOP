package com.project.guitarShop.item.app;

import com.project.guitarShop.item.domain.Brand;
import com.project.guitarShop.item.domain.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Test
    void save() {
        //given
        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);

        //when
        ItemResponse saveItem = itemService.save(itemRequest);

        //then
        assertEquals("name", saveItem.getName());
        assertEquals(10000, saveItem.getPrice());
        assertEquals(10, saveItem.getQuantity());
        assertEquals(Category.ELECTRIC_GUITAR, saveItem.getCategory());
        assertEquals(Brand.JAMESTYLER, saveItem.getBrand());
    }

    @Test
    void findAllBySortPriceAscending() {
        ItemRequest itemRequest1 = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest2 = new ItemRequest("name", 20000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest3 = new ItemRequest("name", 30000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest4 = new ItemRequest("name", 40000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest5 = new ItemRequest("name", 50000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest1);
        itemService.save(itemRequest2);
        itemService.save(itemRequest3);
        itemService.save(itemRequest4);
        itemService.save(itemRequest5);

        //given
        List<ItemResponse> items = itemService.findALlBySortPrice(true);

        //when //then
        assertThat(items).isNotEmpty();

        int prevPrice = Integer.MIN_VALUE;
        for (ItemResponse item : items) {
            assertThat(item.getPrice()).isGreaterThanOrEqualTo(prevPrice);
            prevPrice = item.getPrice();
        }
    }

    @Test
    void findAllBySortPriceDescending() {
        ItemRequest itemRequest1 = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest2 = new ItemRequest("name", 20000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest3 = new ItemRequest("name", 30000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest4 = new ItemRequest("name", 40000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest5 = new ItemRequest("name", 50000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest1);
        itemService.save(itemRequest2);
        itemService.save(itemRequest3);
        itemService.save(itemRequest4);
        itemService.save(itemRequest5);

        //given
        List<ItemResponse> items = itemService.findALlBySortPrice(false);

        //when //then
        assertThat(items).isNotEmpty();

        int prevPrice = Integer.MAX_VALUE;
        for (ItemResponse item : items) {
            assertThat(item.getPrice()).isLessThanOrEqualTo(prevPrice);
            prevPrice = item.getPrice();
        }
    }

}