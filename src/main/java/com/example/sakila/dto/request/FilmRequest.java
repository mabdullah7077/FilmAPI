package com.example.sakila.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@Getter
public class FilmRequest {

    private final String title;
    private final String description;
    private final Year releaseYear;
    private final double rentalRate;
    private final Short length;
    private final String rating;
    private final Short languageId;  // Foreign key to the Language table
    private final List<Short> actorIds;  // List of actor IDs
}
