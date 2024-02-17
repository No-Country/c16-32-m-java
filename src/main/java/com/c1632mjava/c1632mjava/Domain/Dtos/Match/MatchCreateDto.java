package com.c1632mjava.c1632mjava.Domain.Dtos.Match;

import jakarta.validation.constraints.NotNull;

public record MatchCreateDto(
    Float compatibilityPercentage,
    @NotNull
    Long user1,
    @NotNull
    Long user2
) {
}
