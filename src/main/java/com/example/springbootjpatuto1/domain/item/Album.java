package com.example.springbootjpatuto1.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Album extends Item {
    @Column(name = "ARTIST")
    private String artist;
    @Column(name = "ETC")
    private String etc;
}
