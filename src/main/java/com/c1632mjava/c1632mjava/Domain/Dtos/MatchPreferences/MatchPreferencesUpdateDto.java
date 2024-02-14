package com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.CompatibilityPercentage;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Distance;
import jakarta.validation.constraints.NotNull;

public record MatchPreferencesUpdateDto(
        @NotNull Long userId,
        @NotNull boolean female,
        @NotNull boolean male,
        @NotNull boolean other,
        @NotNull int minAge,
        @NotNull int maxAge,
        Distance distance,
        CompatibilityPercentage compatibilityPercentage,
        @NotNull boolean longTermRelationship,
        @NotNull boolean justFriends,
        @NotNull boolean rightNow
        ) {
}
