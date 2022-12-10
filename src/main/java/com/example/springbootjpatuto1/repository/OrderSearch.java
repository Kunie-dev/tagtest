package com.example.springbootjpatuto1.repository;

import com.example.springbootjpatuto1.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
    private OrderStatus orderStatus;
    private String memberName;
}
