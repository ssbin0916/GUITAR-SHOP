package com.project.guitarShop.service.cart;

import com.project.guitarShop.entity.cart.Cart;
import com.project.guitarShop.entity.cartItem.CartItem;
import com.project.guitarShop.entity.item.Item;
import com.project.guitarShop.entity.member.Member;
import com.project.guitarShop.exception.cart.NotFoundCartException;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.exception.member.NotFoundMemberException;
import com.project.guitarShop.repository.cart.CartRepository;
import com.project.guitarShop.repository.item.ItemRepository;
import com.project.guitarShop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public Long addCart(Long memberId, Long itemId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundItemException("해당 상품을 찾을 수 없습니다."));

        CartItem cartItem = CartItem.createCartItem(item, item.getName(), item.getPrice());

        Cart cart = Cart.createCart(member, cartItem);

        cartRepository.save(cart);

        return cart.getId();
    }

    @Transactional
    @Override
    public void remove(Long cartId) {

        cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundCartException("해당 장바구니를 찾을 수 없습니다."));

        cartRepository.deleteById(cartId);
    }

}
