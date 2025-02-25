package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "language")
@Getter
public class Language {

    @Id
    @Column(name = "language_id")
    private Short id;

    @Column(name = "name")
    private String name;
}
