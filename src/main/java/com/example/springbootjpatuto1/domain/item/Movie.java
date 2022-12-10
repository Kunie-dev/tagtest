package com.example.springbootjpatuto1.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "actor")
    private String actor;
}
