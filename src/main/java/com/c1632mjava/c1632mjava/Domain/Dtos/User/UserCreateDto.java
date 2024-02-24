package com.c1632mjava.c1632mjava.Domain.Dtos.User;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import jakarta.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.With;
import java.time.LocalDate;

@With
public record UserCreateDto(
    @NotNull String name,
    @NotNull String password,
    @NotNull @Email String email,
    @JsonFormat (pattern = "yyyy-MM-dd")
    @NotNull LocalDate birthdate,
    @NotNull String photo,
    Gender gender,
    @NotNull String pronouns,
    @NotNull String description){
}

//TODO. Revisar validation constrains para birthdate (pasar a LocalDate), gender, password.
/*
Para password e email se puede hacer una RegExp con jackson, a través de la anotación @Email
 */

