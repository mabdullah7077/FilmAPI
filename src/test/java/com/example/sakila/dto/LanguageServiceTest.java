package com.example.sakila.dto;


import com.example.sakila.dto.request.LanguageRequest;
import com.example.sakila.dto.response.LanguageResponse;
import com.example.sakila.entities.Language;
import com.example.sakila.repos.LanguageRepo;
import com.example.sakila.services.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageServiceTest {

    private LanguageRepo languageRepo;
    private LanguageService languageService;

    @BeforeEach
    void setUp() {
        languageRepo = mock(LanguageRepo.class);
        languageService = new LanguageService(languageRepo);
    }

    @Test
    public void testGetAllLanguagesReturnsLanguages() {

        Language language1 = new Language((short) 1, "English");
        Language language2 = new Language((short) 2, "Spanish");

        List<Language> languages = List.of(language1, language2);
        when(languageRepo.findAll()).thenReturn(languages);

        List<LanguageResponse> languageResponses = languageService.getAllLanguages();

        assertEquals(2, languageResponses.size());
        assertEquals("English", languageResponses.get(0).getName());
        assertEquals("Spanish", languageResponses.get(1).getName());
        verify(languageRepo, times(1)).findAll();
    }

    @Test
    public void testGetLanguageByIdReturnsLanguageResponseWhenLanguageExists() {

        Short languageId = 1;
        Language language = new Language(languageId, "English");

        when(languageRepo.findById(languageId)).thenReturn(Optional.of(language));

        LanguageResponse languageResponse = languageService.getLanguageById(languageId);

        assertNotNull(languageResponse);
        assertEquals("English", languageResponse.getName());
        verify(languageRepo, times(1)).findById(languageId);
    }

    @Test
    public void testGetLanguageByIdThrowsExceptionWhenLanguageNotFound() {

        Short languageId = 1;
        when(languageRepo.findById(languageId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> languageService.getLanguageById(languageId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    public void testCreateLanguageReturnsLanguageResponse() {

        LanguageRequest languageRequest = new LanguageRequest((short) 1,"French");
        Language savedLanguage = new Language((short) 1, "French");
        when(languageRepo.save(any(Language.class))).thenReturn(savedLanguage);

        LanguageResponse languageResponse = languageService.createLanguage(languageRequest);

        assertNotNull(languageResponse);
        assertEquals("French", languageResponse.getName());
        verify(languageRepo, times(1)).save(any(Language.class));
    }

    @Test
    public void testUpdateLanguageReturnsUpdatedLanguageResponse() {

        Short id = 1;
        Language language = new Language(id, "English");
        LanguageRequest languageRequest = new LanguageRequest(id,"German");

        when(languageRepo.findById(id)).thenReturn(Optional.of(language));
        when(languageRepo.save(any(Language.class))).thenReturn(language);

        LanguageResponse languageResponse = languageService.updateLanguage(id, languageRequest);

        assertNotNull(languageResponse);
        assertEquals("German", languageResponse.getName());
        verify(languageRepo, times(1)).save(any(Language.class));
    }

    @Test
    public void testDeleteLanguageDeletesLanguageWhenLanguageExists() {

        Short languageId = 1;
        Language language = new Language(languageId, "English");

        when(languageRepo.findById(languageId)).thenReturn(Optional.of(language));

        languageService.deleteLanguage(languageId);

        verify(languageRepo, times(1)).delete(language);
    }

    @Test
    public void testDeleteLanguageThrowsExceptionWhenLanguageNotFound() {

        Short languageId = 1;
        when(languageRepo.findById(languageId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> languageService.deleteLanguage(languageId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
