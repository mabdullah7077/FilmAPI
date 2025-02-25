package com.example.sakila.repos;

import com.example.sakila.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepo extends JpaRepository<Language, Short> {
}