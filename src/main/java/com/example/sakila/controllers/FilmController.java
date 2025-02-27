package com.example.sakila.controllers;

import com.example.sakila.dto.request.FilmPatchRequest;
import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.request.LanguageRequest;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.services.FilmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<FilmResponse> listFilms(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String title) {

        return filmService.listFilms(categoryName, releaseYear, rating, title);
    }

    @GetMapping("/films/{id}")
    public FilmResponse getFilmById(@PathVariable Short id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/films/language")
    public List<FilmResponse> listFilmsByLanguage(@RequestParam String language) {
        return filmService.listFilmsByLanguage(language);
    }

    @PostMapping("/films")
    public FilmResponse createFilm(@Valid @RequestBody FilmRequest data) {
        return filmService.createFilm(data);
    }

    @PatchMapping("/films/{id}")
    public FilmResponse patchFilm(@PathVariable Short id, @Valid @RequestBody FilmPatchRequest data) {
        return filmService.patchFilm(id, data);
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
    }
}
