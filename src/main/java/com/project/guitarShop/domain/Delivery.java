package com.project.guitarShop.domain;

import com.project.guitarShop.domain.member.Member;
import lombok.Getter;

@Getter
public class Delivery {

    private Long id;

    private Order order;

    private Member member;


}
