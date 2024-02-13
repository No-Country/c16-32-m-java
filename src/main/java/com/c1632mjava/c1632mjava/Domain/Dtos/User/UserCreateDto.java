package com.c1632mjava.c1632mjava.Domain.Dtos.User;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UserCreateDto(
    @NotNull String name,
    @NotNull String password,
    @NotNull String email,
    @NotNull LocalDateTime birthdate,
    @NotNull String photo,
    @NotBlank Gender gender,
    @NotNull String pronouns,
    @NotNull String description){
}



