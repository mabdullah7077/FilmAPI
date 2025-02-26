package com.example.sakila.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.util.List;

@Getter
@Setter
public class FilmPatchRequest {

    @Size(max = 128, message = "Title cannot be longer than 128 characters")
    private String title;

    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    private String description;

    private Year releaseYear;

    @Min(value = 1, message = "Length must be at least 1 minute")
    private Short length;

    private List<Short> actorIds;

    private Short languageId;

    private String rating;

    private List<Short> categoryIds;
}
