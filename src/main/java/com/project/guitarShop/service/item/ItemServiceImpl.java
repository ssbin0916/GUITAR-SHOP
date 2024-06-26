package com.project.guitarShop.service.item;

import com.project.guitarShop.dto.item.ItemRequest.AddItemRequest;
import com.project.guitarShop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarShop.dto.item.ItemResponse.AddItemResponse;
import com.project.guitarShop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public List<AddItemResponse> save(List<AddItemRequest> requests) {
        List<Item> items = new ArrayList<>();
        for (AddItemRequest request : requests) {
            if (request.name() == null || request.name().trim().isEmpty()) {
                throw new IllegalArgumentException("아이템 이름은 필수입니다.");
            }
            if (request.category() == null) {
                throw new IllegalArgumentException("아이템 카테고리는 필수입니다.");
            }
            if (request.brand() == null) {
                throw new IllegalArgumentException("아이템 브랜드는 필수입니다.");
            }

            Item item = Item.builder()
                    .name(request.name())
                    .price(request.price())
                    .quantity(request.quantity())
                    .category(request.category())
                    .brand(request.brand())
                    .build();
            items.add(item);
        }

        itemRepository.saveAll(items);
        return items.stream()
                .map(AddItemResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FindItemResponse> search(FindItemRequest request, Pageable pageable) {
        return itemRepository.search(request, pageable);
    }

}



