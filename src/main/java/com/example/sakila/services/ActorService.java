package com.example.sakila.services;

import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.repos.ActorRepo;
import com.example.sakila.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepo actorRepo;
    private final FilmRepo filmRepo;

    @Autowired
    public ActorService(ActorRepo actorRepo, FilmRepo filmRepo) {
        this.actorRepo = actorRepo;
        this.filmRepo = filmRepo;
    }

    public List<ActorResponse> listActors(String name) {
        if (name != null && !name.isEmpty()) {
            return actorRepo.findByFullNameContainingIgnoreCase(name)
                    .stream()
                    .map(ActorResponse::from)
                    .toList();
        } else {
            return actorRepo.findAll()
                    .stream()
                    .map(ActorResponse::from)
                    .toList();
        }
    }

    public ActorResponse getActorById(Short id) {
        Actor actor = actorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));
        return ActorResponse.from(actor);
    }

    public ActorResponse createActor(ActorRequest data) {
        Actor actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());

        List<Film> films = data.getFilmIds()
                .stream()
                .map(filmId -> filmRepo.findById(filmId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No film exists with ID")))
                .toList();

        actor.setFilms(films);
        Actor savedActor = actorRepo.save(actor);
        return ActorResponse.from(savedActor);
    }

    public ActorResponse updateActor(Short id, ActorRequest data) {
        Actor actor = actorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));

        if (data.getFirstName() != null) {
            actor.setFirstName(data.getFirstName());
        }

        if (data.getLastName() != null) {
            actor.setLastName(data.getLastName());
        }

        if (data.getFilmIds() != null) {
            List<Film> films = filmRepo.findAllById(data.getFilmIds());
            actor.setFilms(films);
        }

        Actor updatedActor = actorRepo.save(actor);
        return ActorResponse.from(updatedActor);
    }

    public void deleteActor(Short id) {
        Actor actor = actorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));

        actorRepo.delete(actor);
    }
}
