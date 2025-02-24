package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.repos.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {

    private ActorRepo actorRepo;

    @Autowired
    public ActorController(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    @GetMapping("/actors")
    public List<Actor> listActors(){
        return actorRepo.findAll();
    }

    @GetMapping("/actors/{id}")
    public Actor listActors(@PathVariable Short id){
        return actorRepo.findById(id).orElseThrow();
    }

}

