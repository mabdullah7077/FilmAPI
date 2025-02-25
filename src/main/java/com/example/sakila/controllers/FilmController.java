package com.example.sakila.controllers;

import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repos.ActorRepo;
import com.example.sakila.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {

    private final FilmRepo filmRepo;

    @Autowired
    public FilmController(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @GetMapping("/films")
    public List<FilmResponse> listFilms(@RequestParam(required = false) Optional<String> title){
        return title
                .map(filmRepo::findByTitleContainingIgnoreCase)
                .orElseGet(filmRepo::findAll)
                .stream()
                .map(FilmResponse::from)
                .toList();
    }

    @GetMapping("/films/{id}")
    public FilmResponse listFilms(@PathVariable Short id){
        return filmRepo.findById(id)
                .map(FilmResponse::from)
                .orElseThrow();
    }

}


