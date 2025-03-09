package com.example.sakila.dto;

import com.example.sakila.controllers.LanguageController;
import com.example.sakila.dto.request.LanguageRequest;
import com.example.sakila.dto.response.LanguageResponse;
import com.example.sakila.entities.Language;
import com.example.sakila.services.LanguageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;

public class LanguageControllerTest {

    private final LanguageService languageService = mock(LanguageService.class);
    private final LanguageController languageController = new LanguageController(languageService);

    @Test
    public void getLanguageByIdReturnsLanguageResponseForAValidId() {

        final short id = 1;
        final Language language = new Language(id, "English");
        final LanguageResponse expectedResponse = new LanguageResponse(language.getId(), language.getName());

        doReturn(expectedResponse).when(languageService).getLanguageById(id);

        final LanguageResponse actualResponse = languageController.getLanguageById(id);

        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getName(), actualResponse.getName());
    }

    @Test
    public void getAllLanguagesReturnsListOfLanguageResponses() {

        final List<Language> languages = List.of(
                new Language((short) 1, "English"),
                new Language((short) 2, "Spanish")
        );

        final List<LanguageResponse> expectedResponse = languages.stream()
                .map(language -> new LanguageResponse(language.getId(), language.getName()))
                .toList();

        doReturn(expectedResponse).when(languageService).getAllLanguages();

        final List<LanguageResponse> actualResponse = languageController.getAllLanguages();

        Assertions.assertEquals(expectedResponse.size(), actualResponse.size());
        Assertions.assertEquals(expectedResponse.get(0).getId(), actualResponse.get(0).getId());
        Assertions.assertEquals(expectedResponse.get(1).getId(), actualResponse.get(1).getId());
    }

    @Test
    public void createLanguageReturnsLanguageResponseWhenLanguageIsCreated() {

        final short id = 1;
        final LanguageRequest languageRequest = new LanguageRequest(id, "French");
        final Language createdLanguage = new Language(id, "French");
        final LanguageResponse expectedResponse = new LanguageResponse(createdLanguage.getId(), createdLanguage.getName());

        doReturn(expectedResponse).when(languageService).createLanguage(languageRequest);

        final LanguageResponse actualResponse = languageController.createLanguage(languageRequest);

        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getName(), actualResponse.getName());
    }

    @Test
    public void updateLanguageReturnsUpdatedLanguageResponseWhenLanguageIsUpdated() {

        final short id = 1;
        final LanguageRequest languageRequest = new LanguageRequest(id, "German");
        final Language updatedLanguage = new Language(id, "German");
        final LanguageResponse expectedResponse = new LanguageResponse(updatedLanguage.getId(), updatedLanguage.getName());

        doReturn(expectedResponse).when(languageService).updateLanguage(id, languageRequest);

        final LanguageResponse actualResponse = languageController.updateLanguage(id, languageRequest);

        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getName(), actualResponse.getName());
    }

    @Test
    public void deleteLanguageDeletesLanguageWhenIdIsValid() {

        final short id = 1;

        doNothing().when(languageService).deleteLanguage(id);

        languageController.deleteLanguage(id);

        verify(languageService, times(1)).deleteLanguage(id);
    }
}
