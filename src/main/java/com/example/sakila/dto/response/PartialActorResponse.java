package com.example.sakila.dto.response;

import com.example.sakila.entities.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PartialActorResponse {
    private final Short id;
    private final String firstName;
    private final String lastName;



    public static PartialActorResponse from(Actor actor) {
        return new PartialActorResponse(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName()
        );
    }
}