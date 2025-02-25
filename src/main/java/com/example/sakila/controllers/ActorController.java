package com.example.sakila.controllers;

import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.repos.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    private final ActorRepo actorRepo;

    @Autowired
    public ActorController(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
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

}

