package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "language")
@Getter
@Setter
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // This tells Hibernate to auto-generate the ID
    @Column(name = "language_id")
    private Short id;

    @Column(name = "name")
    private String name;
}
