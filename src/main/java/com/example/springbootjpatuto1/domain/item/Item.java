package com.example.springbootjpatuto1.domain.item;

import com.example.springbootjpatuto1.domain.BaseEntity;
import com.example.springbootjpatuto1.domain.Category;
import com.example.springbootjpatuto1.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "K_ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
public abstract class Item extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    @Setter
    private Long id;
    @Column(name = "NAME")
    @Setter
    private String name;
    @Column(name = "PRICE")
    @Setter
    private int price;
    @Column(name = "STOCKQUANTITY")
    @Setter
    private int stockQuantity;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= quantity;
    }
}
