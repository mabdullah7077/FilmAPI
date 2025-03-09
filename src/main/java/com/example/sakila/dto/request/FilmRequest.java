package com.example.sakila.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@Getter
public class FilmRequest {

    @NotNull
    @Size(max = 128)
    private final String title;

    @NotNull
    @Size(max = 500)
    private final String description;

    @NotNull
    private final Year releaseYear;

    @NotNull
    @Size(min = 1)
    private final Short length;

    @NotNull
    @Size(max = 5)
    private final String rating;

    @NotNull
    private final Short languageId;

    @NotNull
    private final List<Short> actorIds;

    private final List<Short> categoryIds;
}
