package com.example.springbootjpatuto1.domain.order.delivery;

import com.example.springbootjpatuto1.domain.BaseEntity;
import com.example.springbootjpatuto1.domain.order.Order;
import com.example.springbootjpatuto1.domain.type.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "K_DELIVERY")
@Getter
public class Delivery extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "ZIPCODE")),
    })
    @Setter
    private Address address;

    @Setter
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
}
