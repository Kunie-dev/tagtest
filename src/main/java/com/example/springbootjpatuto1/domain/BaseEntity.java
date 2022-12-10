package com.example.springbootjpatuto1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED")
    private LocalDateTime created;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED")
    private LocalDateTime updated;
}
