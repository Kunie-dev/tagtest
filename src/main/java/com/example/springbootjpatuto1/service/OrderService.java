package com.example.springbootjpatuto1.service;

import com.example.springbootjpatuto1.domain.item.Item;
import com.example.springbootjpatuto1.domain.order.Order;
import com.example.springbootjpatuto1.domain.order.OrderItem;
import com.example.springbootjpatuto1.domain.order.delivery.Delivery;
import com.example.springbootjpatuto1.domain.user.Member;
import com.example.springbootjpatuto1.repository.ItemRepository;
import com.example.springbootjpatuto1.repository.MemberRepository;
import com.example.springbootjpatuto1.repository.OrderRepository;
import com.example.springbootjpatuto1.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
