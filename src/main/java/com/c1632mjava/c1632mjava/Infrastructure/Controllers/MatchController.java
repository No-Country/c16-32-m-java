package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Match.MatchReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<MatchReadDto>> findAllMatchesByUserId(@PageableDefault(size = 10) Pageable paging,
                                                                     @PathVariable Long userId){
        return ResponseEntity.ok(this.matchService.findAllMatchesByUserId(userId, paging));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id){
        this.matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    // hacer endpoint para crear un match SOLO PARA PRUEBAS
}
