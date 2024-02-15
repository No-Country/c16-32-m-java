package com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage;

import java.time.LocalDateTime;

public record ReportedMessageReadDto(
    Long id,
    LocalDateTime date,
    String message) {
}
