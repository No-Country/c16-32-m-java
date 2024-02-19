package com.c1632mjava.c1632mjava.Domain.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthResponse(@NotNull String token) {
}
