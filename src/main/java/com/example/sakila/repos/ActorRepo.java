package com.example.sakila.repos;

import com.example.sakila.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepo extends JpaRepository<Actor, Short> {

    List<Actor> findByFullNameContainingIgnoreCase(String firstName);

}
