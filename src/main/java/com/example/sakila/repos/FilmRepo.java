package com.example.sakila.repos;

import com.example.sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo extends JpaRepository<Film, Short> {
}
