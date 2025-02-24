package com.example.sakila.repos;

import com.example.sakila.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepo extends JpaRepository<Actor, Short> {
}
