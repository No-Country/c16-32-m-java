package com.c1632mjava.c1632mjava.Domain.Entities;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.CompatibilityPercentage;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Distance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="match_preferences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchPreferences implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchPreferenceId;

    private Long userId;

    private boolean female;
    private boolean male;
    private boolean other;

    private int minAge;
    private int maxAge;

    @Enumerated(EnumType.STRING)
    private Distance distance;

    @Enumerated(EnumType.STRING)
    private CompatibilityPercentage compatibilityPercentage;

    private boolean longTermRelationship;
    private boolean justFriends;
    private boolean rightNow;

    private boolean active; //TODO. Debería desactivarse cuando pasa a false el User anexo.

}
