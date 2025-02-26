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
    private final Short length;
    private final String rating;
    private final Short languageId;
    private final List<Short> actorIds;
    private final List<Short> categoryIds;
}
