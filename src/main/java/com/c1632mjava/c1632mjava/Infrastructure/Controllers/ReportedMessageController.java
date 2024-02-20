package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.ReportedMessage.ReportedMessageReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.ReportedMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reported-messages")
@RequiredArgsConstructor
public class ReportedMessageController {
    private final ReportedMessageService reportedMessageService;

    @PostMapping
    public ResponseEntity<ReportedMessageReadDto> create(@RequestBody @Valid ReportedMessageCreateDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.reportedMessageService.create(dto));
    }
}
