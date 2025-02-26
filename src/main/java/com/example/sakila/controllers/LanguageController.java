package com.example.sakila.controllers;

import com.example.sakila.dto.request.LanguageRequest;
import com.example.sakila.dto.response.LanguageResponse;
import com.example.sakila.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")  // The base URL for this controller
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    // Endpoint to create a new language
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageResponse createLanguage(@RequestBody LanguageRequest languageRequest) {
        return languageService.createLanguage(languageRequest);
    }

    // Endpoint to fetch a language by its ID
    @GetMapping("/{id}")
    public LanguageResponse getLanguageById(@PathVariable Short id) {
        return languageService.getLanguageById(id);
    }

    // Endpoint to update an existing language
    @PutMapping("/{id}")
    public LanguageResponse updateLanguage(@PathVariable Short id, @RequestBody LanguageRequest languageRequest) {
        return languageService.updateLanguage(id, languageRequest);
    }

    // Endpoint to delete a language by its ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLanguage(@PathVariable Short id) {
        languageService.deleteLanguage(id);
    }

    // Endpoint to get all languages
    @GetMapping
    public List<LanguageResponse> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}
