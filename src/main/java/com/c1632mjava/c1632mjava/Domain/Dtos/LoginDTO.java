package com.c1632mjava.c1632mjava.Domain.Dtos;

import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;

public record LoginDTO(
        @Valid @NotNull
        String email,
        @Valid @NotNull
        String password) {
}