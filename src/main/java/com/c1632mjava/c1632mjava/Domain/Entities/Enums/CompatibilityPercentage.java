package com.c1632mjava.c1632mjava.Domain.Entities.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CompatibilityPercentage {
    MELODIAS_GEMELAS("91% a 100%"),
    RITMO_PERFECTO("81% a 90%"),
    BUENOS_ACORDES("71% a 80%"),
    NOTAS_SIMILARES("55% a 70%"),
    NO_DESAFINAN("40% a 54%");

    private final String name;
}


/*Compás Coincidente*/