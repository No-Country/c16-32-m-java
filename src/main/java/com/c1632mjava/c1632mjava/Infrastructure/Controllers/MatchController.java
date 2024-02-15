package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/{id}")
    public ResponseEntity<MatchReadDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.matchService.findById(id));
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<MatchReadDto>> findAllByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(this.matchService.findAllByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.matchService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // hacer endpoint para crear un match SOLO PARA PRUEBAS
}
