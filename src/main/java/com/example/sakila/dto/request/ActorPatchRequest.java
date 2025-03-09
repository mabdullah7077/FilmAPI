package com.example.sakila.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorPatchRequest {

    @Size(max = 45)
    private String firstName;

    @Size(max = 45)
    private String lastName;

    private List<Short> filmIds;
}
