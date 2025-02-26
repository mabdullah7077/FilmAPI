package com.example.sakila.services;

import com.example.sakila.dto.request.FilmRequest;
import com.example.sakila.dto.request.LanguageRequest;
import com.example.sakila.dto.response.FilmResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Category;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.repos.ActorRepo;
import com.example.sakila.repos.CategoryRepo;
import com.example.sakila.repos.FilmRepo;
import com.example.sakila.repos.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmRepo filmRepo;
    private final ActorRepo actorRepo;
    private final LanguageRepo languageRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public FilmService(FilmRepo filmRepo, ActorRepo actorRepo, LanguageRepo languageRepo, CategoryRepo categoryRepo) {
        this.filmRepo = filmRepo;
        this.actorRepo = actorRepo;
        this.languageRepo = languageRepo;
        this.categoryRepo = categoryRepo;
    }


    public List<FilmResponse> listFilms(String categoryName, Integer releaseYear, String rating, String title) {
        List<Film> films = filmRepo.findAll();

        if (title != null && !title.isEmpty()) {
            films = filmRepo.findByTitleContainingIgnoreCase(title);
        }

        if (categoryName != null && !categoryName.isEmpty()) {
            films = filmRepo.findByCategoriesNameIgnoreCase(categoryName);
        }

        if (releaseYear != null) {
            films = filmRepo.findByReleaseYear(Year.of(releaseYear));
        }

        if (rating != null && !rating.isEmpty()) {
            films = filmRepo.findByRating(rating);
        }

        if (films.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No films found matching the criteria.");
        }

        return films.stream()
                .map(FilmResponse::from)
                .collect(Collectors.toList());
    }


    public FilmResponse getFilmById(Short id) {
        return filmRepo.findById(id)
                .map(FilmResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found"));
    }

    public List<FilmResponse> listFilmsByLanguage(String language) {
        return filmRepo.findByLanguageNameIgnoreCase(language)
                .stream()
                .map(FilmResponse::from)
                .collect(Collectors.toList());
    }


    public FilmResponse createFilm(FilmRequest data) {
        Language language = languageRepo.findById(data.getLanguageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language not found"));

        List<Actor> actors = actorRepo.findAllById(data.getActorIds());

        List<Category> categories = categoryRepo.findAllById(data.getCategoryIds());


        Film film = new Film();
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());
        film.setLength(data.getLength());
        film.setRating(data.getRating());
        film.setLanguage(language);
        film.setActors(actors);
        film.setCategories(categories);

        Film savedFilm = filmRepo.save(film);
        return FilmResponse.from(savedFilm);
    }


    public FilmResponse updateFilm(Short id, FilmRequest data) {
        Film film = filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found"));

        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());
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


    public FilmResponse updateFilmLanguage(Short id, LanguageRequest languageRequest) {
        Film film = filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found"));

        Language language = languageRepo.findById(languageRequest.getLanguageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));

        film.setLanguage(language);

        Film updatedFilm = filmRepo.save(film);
        return FilmResponse.from(updatedFilm);
    }


    public void deleteFilm(Short id) {
        Film film = filmRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found"));

        filmRepo.delete(film);
    }
}
