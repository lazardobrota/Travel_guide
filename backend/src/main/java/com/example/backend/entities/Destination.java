package com.example.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Destination {

    private int id;

    @NotNull(message = "Name field can't be null")
    @NotEmpty(message = "Name field can't be empty")
    private String name;

    @NotNull(message = "Description field can't be null")
    @NotEmpty(message = "Description field can't be empty")
    private String description;

    private List<Article> articles;
}
