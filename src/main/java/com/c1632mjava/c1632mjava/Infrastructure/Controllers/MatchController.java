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
    public ResponseEntity<MatchReadDto> findMatchById(@PathVariable Long id){
        return ResponseEntity.ok(this.matchService.findMatchById(id));
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<MatchReadDto>> findAllMatchesByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(this.matchService.findAllMatchesByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id){
        this.matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    // hacer endpoint para crear un match SOLO PARA PRUEBAS
}
