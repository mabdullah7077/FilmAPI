package com.example.sakila.controllers;

import com.example.sakila.dto.request.ActorPatchRequest;
import com.example.sakila.dto.request.ActorRequest;
import com.example.sakila.dto.response.ActorResponse;
import com.example.sakila.services.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public List<ActorResponse> listActors(@RequestParam(required = false) String name) {
        return actorService.listActors(name);
    }

    @GetMapping("/actors/{id}")
    public ActorResponse getActorById(@PathVariable Short id) {
        return actorService.getActorById(id);
    }

    @PostMapping("/actors")
    public ActorResponse createActor(@Valid @RequestBody ActorRequest actorRequest) {
        return actorService.createActor(actorRequest);
    }

    @PatchMapping("/actors/{id}")
    public ActorResponse updateActor(@PathVariable Short id, @Valid @RequestBody ActorPatchRequest data) {
        return actorService.updateActor(id, data);
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable Short id) {
        actorService.deleteActor(id);
    }
}
