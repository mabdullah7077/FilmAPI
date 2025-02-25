package com.example.sakila.controllers;

import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.repos.ActorRepo;
import com.example.sakila.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    private final ActorRepo actorRepo;
    private final FilmRepo filmRepo;

    @Autowired
    public ActorController(ActorRepo actorRepo, FilmRepo filmRepo) {
        this.actorRepo = actorRepo;
        this.filmRepo = filmRepo;
    }

    @GetMapping("/actors")
    public List<ActorResponse> listActors(@RequestParam(required = false) Optional<String> name){
        return name
                .map(actorRepo::findByFullNameContainingIgnoreCase)
                .orElseGet(actorRepo::findAll)
                .stream()
                .map(ActorResponse::from)
                .toList();
    }

    @GetMapping("/actors/{id}")
    public ActorResponse listActors(@PathVariable Short id){
        return actorRepo.findById(id)
                .map(ActorResponse::from)
                .orElseThrow();
    }

    @PostMapping("/actors")
    public ActorResponse createActor(@RequestBody ActorRequest data) {
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());

        final var films = data.getFilmIds()
                .stream()
                .map(filmId -> filmRepo.findById(filmId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No film exists with ID")))
                .toList();

        actor.setFilms(films);


        final var savedActor = actorRepo.save(actor);
        return ActorResponse.from(savedActor);
    }

    @PutMapping("/actors/{id}")
    public ActorResponse updateActor(@PathVariable Short id, @RequestBody ActorRequest data) {
        Actor actor = actorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));


        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());

        Actor updatedActor = actorRepo.save(actor);

        return ActorResponse.from(updatedActor);
    }

}

