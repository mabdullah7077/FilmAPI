package com.example.sakila.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LanguageRequest {
    private Short languageId;
    private final String name;
}
