package com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.CompatibilityPercentage;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Distance;
import jakarta.validation.constraints.NotNull;

public record MatchPreferencesReadDto(
        Long userId,
        boolean female, boolean male, boolean other,
        int minAge, int maxAge,
        Distance distance,
        CompatibilityPercentage compatibilityPercentage,
        boolean longTermRelationship, boolean justFriends, boolean rightNow
        ) {
}
