package com.example.sakila.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ActorRequest {

    @NotNull
    @Size(min = 1, max = 45)
    private final String firstName;

    @NotNull
    @Size(min = 1, max = 45)
    private final String lastName;


    private final List<Short> filmIds;
}
