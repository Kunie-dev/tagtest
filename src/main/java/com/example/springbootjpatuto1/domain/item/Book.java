package com.example.springbootjpatuto1.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
public class Book extends Item {
    @Column(name = "AUTHOR")
    @Setter
    private String author;
    @Column(name = "ISBN")
    @Setter
    private String isbn;
}
