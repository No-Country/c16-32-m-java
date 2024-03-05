package com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportedMessageCreateDto(
    @NotNull(message = "El mensaje del reporte no puede ser nulo.")
    @NotBlank(message = "El mensaje del reporte no puede estar vacío.")
    String message,
    @NotNull(message = "El ID del chat no puede ser nulo.")
    @Min(message = "El ID del chat no puede ser menor a 1.", value = 1)
    Long chatId) {
}
