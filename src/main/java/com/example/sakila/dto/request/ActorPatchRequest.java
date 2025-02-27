package com.example.sakila.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActorPatchRequest {

    @Size(max = 45)
    private String firstName;

    @Size(max = 45)
    private String lastName;

    private List<Short> filmIds;
}
