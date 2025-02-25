package com.example.sakila.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ActorRequest {
    private final String firstName;
    private final String lastName;
    private final List<Short> filmIds;
}
