package com.example.sakila.dto.response;

import com.example.sakila.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LanguageResponse {
    private final Short id;
    private final String name;

    // Static method to create LanguageResponse from Language entity
    public static LanguageResponse from(Language language) {
        // Ensure the language entity passed has valid fields for 'id' and 'name'
        return new LanguageResponse(
                language.getId(),  // Get the id from the Language entity
                language.getName()  // Get the name from the Language entity
        );
    }
}
