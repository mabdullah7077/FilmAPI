package com.example.sakila.repos;

import com.example.sakila.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Short> {
}
