package com.example.sakila.dto.response;

import com.example.sakila.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LanguageResponse {
    private final Short id;
    private final String name;

    public static LanguageResponse from(Language language) {
        return new LanguageResponse(
                language.getId(),
                language.getName()
        );
    }
}
