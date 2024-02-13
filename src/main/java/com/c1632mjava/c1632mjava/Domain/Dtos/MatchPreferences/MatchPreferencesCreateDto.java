package com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.CompatibilityPercentage;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Distance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatchPreferencesCreateDto(
        @NotNull Long userId,
        @NotNull boolean female,
        @NotNull boolean male,
        @NotNull boolean other,
        @NotNull int minAge,
        @NotNull int maxAge,
        @NotBlank Distance distance,
        @NotBlank CompatibilityPercentage compatibilityPercentage,
        @NotNull boolean longTermRelationship,
        @NotNull boolean justFriends,
        @NotNull boolean rightNow
        ) {
}
