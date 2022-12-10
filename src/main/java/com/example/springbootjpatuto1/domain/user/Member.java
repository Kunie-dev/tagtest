package com.example.springbootjpatuto1.domain.user;

import com.example.springbootjpatuto1.domain.order.Order;
import com.example.springbootjpatuto1.domain.type.Address;
import com.example.springbootjpatuto1.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "K_MEMBER")
@Getter
@Setter
public class Member extends BaseEntity {
    @Id  @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    private int age;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "ZIPCODE")),
    })
    private Address address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}
