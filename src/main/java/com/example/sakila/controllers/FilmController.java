package com.example.sakila.controllers;

import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.request.LanguageRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.repos.ActorRepo;
import com.example.sakila.repos.FilmRepo;
import com.example.sakila.repos.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FilmController {

    private final FilmRepo filmRepo;
    private final ActorRepo actorRepo;
    private final LanguageRepo languageRepo;

    @Autowired
    public FilmController(FilmRepo filmRepo, ActorRepo actorRepo, LanguageRepo languageRepo) {
        this.filmRepo = filmRepo;
        this.actorRepo = actorRepo;
        this.languageRepo = languageRepo;
    }

    @GetMapping("/films")
    public List<FilmResponse> listFilmsByTitle(@RequestParam(required = false) Optional<String> title){
        return title
                .map(filmRepo::findByTitleContainingIgnoreCase)
                .orElseGet(filmRepo::findAll)
                .stream()
                .map(FilmResponse::from)
                .toList();
    }

    @GetMapping("/films/{id}")
    public FilmResponse listFilmsById(@PathVariable Short id){
        return filmRepo.findById(id)
                .map(FilmResponse::from)
                .orElseThrow();
    }

    @GetMapping("/films/language")
    public List<FilmResponse> listFilmsByLanguage(@RequestParam String language) {
        return filmRepo.findByLanguageNameIgnoreCase(language)
                .stream()
                .map(FilmResponse::from)
                .toList();
    }

    @PostMapping("/films")
    public FilmResponse createFilm(@RequestBody FilmRequest data) {

        Language language = languageRepo.findById(data.getLanguageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language not found"));


        List<Actor> actors = actorRepo.findAllById(data.getActorIds());


        Film film = new Film();
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());
        film.setRentalRate(data.getRentalRate());
        film.setLength(data.getLength());
        film.setRating(data.getRating());
        film.setLanguage(language);
        film.setActors(actors);

        Film savedFilm = filmRepo.save(film);

        return FilmResponse.from(savedFilm);
    }

    @PutMapping("/films/{id}")
    public FilmResponse updateFilm(@PathVariable Short id, @RequestBody FilmRequest data) {
        // Find the film by ID
        Film film = filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found"));

        // Update the film's properties
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());
        film.setRentalRate(data.getRentalRate());
        film.setLength(data.getLength());
        film.setRating(data.getRating());


        Language language = languageRepo.findById(data.getLanguageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language not found"));
        film.setLanguage(language);

        List<Actor> actors = data.getActorIds().stream()
                .map(actorId -> actorRepo.findById(actorId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Actor not found"))
                )
                .collect(Collectors.toList());
        film.setActors(actors);

        Film updatedFilm = filmRepo.save(film);

        return FilmResponse.from(updatedFilm);
    }

    @PutMapping("/films/{id}/language")
    public FilmResponse updateFilmLanguage(@PathVariable Short id, @RequestBody LanguageRequest languageRequest) {
        Film film = filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found"));

        Language language = languageRepo.findById(languageRequest.getLanguageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));

        film.setLanguage(language);

        Film updatedFilm = filmRepo.save(film);

        return FilmResponse.from(updatedFilm);
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Short id) {

        Film film = filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found"));

        filmRepo.delete(film);
    }



}


