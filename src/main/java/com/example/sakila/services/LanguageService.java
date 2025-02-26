package com.example.sakila.services;

import com.example.sakila.dto.request.LanguageRequest;
import com.example.sakila.dto.response.LanguageResponse;
import com.example.sakila.entities.Language;
import com.example.sakila.repos.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    private final LanguageRepo languageRepo;

    @Autowired
    public LanguageService(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
    }

    // Fetch Language by ID
    public LanguageResponse getLanguageById(Short id) {
        Language language = languageRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));
        return new LanguageResponse(language.getId(), language.getName());  // return LanguageResponse
    }

    public List<LanguageResponse> getAllLanguages() {
        List<Language> languages = languageRepo.findAll();  // Fetch all languages from the repository
        return languages.stream()
                .map(language -> new LanguageResponse(language.getId(), language.getName()))  // Map to LanguageResponse
                .collect(Collectors.toList());
    }


    public LanguageResponse createLanguage(LanguageRequest data) {
        Language language = new Language();
        language.setName(data.getName());  // Assuming the request contains the name of the language


        Language savedLanguage = languageRepo.save(language);  // This returns a Language object

        return LanguageResponse.from(savedLanguage);  // This is where the conversion to LanguageResponse happens
    }

    // Update an existing Language
    public LanguageResponse updateLanguage(Short id, LanguageRequest data) {
        Language language = languageRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));

        language.setName(data.getName());

        Language updatedLanguage = languageRepo.save(language);  // Saving the updated language

        return LanguageResponse.from(updatedLanguage);  // Returning the updated language as a response
    }

    // Delete a Language by ID
    public void deleteLanguage(Short id) {
        Language language = languageRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));

        languageRepo.delete(language);  // Deleting the language
    }
}
