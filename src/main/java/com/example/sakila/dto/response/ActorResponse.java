package com.example.sakila.dto.response;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ActorResponse {

    private final Short id;
    private final String firstName;
    private final String lastName;
    private final List<PartialFilmResponse> films;

    public static ActorResponse from(Actor actor) {
        return new ActorResponse(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                actor.getFilms()
                        .stream()
                        .map(PartialFilmResponse::from)
                        .toList()
        );
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

}
