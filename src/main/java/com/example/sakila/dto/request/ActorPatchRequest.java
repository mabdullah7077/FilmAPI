package com.example.sakila.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActorPatchRequest {

    @Size(max = 128, message = "First name cannot be longer than 128 characters")
    private String firstName;

    @Size(max = 128, message = "Last name cannot be longer than 128 characters")
    private String lastName;

    private List<Short> filmIds;
}
