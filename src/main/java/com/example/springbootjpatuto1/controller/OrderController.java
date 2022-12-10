package com.example.springbootjpatuto1.controller;

import com.example.springbootjpatuto1.domain.item.Item;
import com.example.springbootjpatuto1.domain.order.Order;
import com.example.springbootjpatuto1.domain.user.Member;
import com.example.springbootjpatuto1.repository.OrderSearch;
import com.example.springbootjpatuto1.service.ItemService;
import com.example.springbootjpatuto1.service.MemberService;
import com.example.springbootjpatuto1.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/orders/new")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "orders/orderForm";
    }

    @PostMapping("/orders")
    public String create(
            @RequestParam("memberId") Long memberId,
            @RequestParam("itemId") Long itemId,
            @RequestParam("count") int count
    ) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders/new";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "orders/orderList";
    }

    @PostMapping("/orders/{id}/cancel")
    public String orderList(@PathVariable Long id) {
        orderService.cancel(id);
        return "redirect:/orders";
    }

}
