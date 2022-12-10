package com.example.springbootjpatuto1.domain;

import com.example.springbootjpatuto1.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "K_CATEGORY")
@Getter
public class Category extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> children = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "K_CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<>();

    public void addChildCategory(Category child) {
        this.children.add(child);
        child.setParent(this);
    }
}