package com.example.sakila.repos;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepo extends JpaRepository<Film, Short> {

    List<Film> findByTitleContainingIgnoreCase(String title);

    List<Film> findByLanguageNameIgnoreCase(String name);

}
