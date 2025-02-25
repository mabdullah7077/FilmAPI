package com.example.sakila.dto.response;

import com.example.sakila.dto.response.PartialFilmResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@Getter
public class FilmResponse {

    private final Short id;
    private final String title;
    private final String description;
    private final Year releaseYear;
    private final double rentalRate;
    private final Short length;
    private final String rating;
    private final List<PartialActorResponse> actors;

    public static com.example.sakila.dto.response.FilmResponse from(Film film) {
        return new com.example.sakila.dto.response.FilmResponse(
                film.getId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getRentalRate(),
                film.getLength(),
                film.getRating(),
                film.getActors()
                        .stream()
                        .map(PartialActorResponse::from)
                        .toList()
        );
    }

}
