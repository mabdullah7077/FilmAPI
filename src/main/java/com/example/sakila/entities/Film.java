package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "film")
@Getter
public class Film {

    @Id
    @Column(name = "film_id")
    private Short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Year releaseYear;

    @Column(name = "rental_rate")
    private double rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "rating")
    private String rating;


    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private List<Actor> actors;



}