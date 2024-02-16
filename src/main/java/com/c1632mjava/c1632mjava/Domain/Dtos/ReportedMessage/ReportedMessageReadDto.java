package com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage;

import java.time.LocalDateTime;

public record ReportedMessageReadDto(
    Long reportedMessageId,
    LocalDateTime date,
    String message) {
}
