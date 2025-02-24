package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repos.ActorRepo;
import com.example.sakila.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    private FilmRepo filmRepo;

    @Autowired
    public FilmController(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @GetMapping("/films")
    public List<Film> listActors(){
        return filmRepo.findAll();
    }

    @GetMapping("/films/{id}")
    public Film listFilms(@PathVariable Short id){
        return filmRepo.findById(id).orElseThrow();
    }

}


