package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.MatchPreferences.*;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferences")
@RequiredArgsConstructor
public class MatchPreferencesController {

    private final MatchPreferencesService matchPreferencesService;

    @PostMapping("/")
    @Transactional
    public ResponseEntity<MatchPreferencesReadDto> createMatchPreferences(@RequestBody @Valid
                                                                    MatchPreferencesCreateDto matchPreferencesCreateDto){
        MatchPreferencesReadDto result = matchPreferencesService.createMatchPreferences(
                matchPreferencesCreateDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MatchPreferencesReadDto> findByUserId (@PathVariable Long id) {
        return ResponseEntity.ok(matchPreferencesService.findPreferencesByUserId(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MatchPreferencesReadDto> updateMatchPreferences (
                                         @RequestBody @Valid
                                         MatchPreferencesUpdateDto matchPreferencesUpdateDto) {
        return ResponseEntity.ok(matchPreferencesService.
                updateMatchPreferences(matchPreferencesUpdateDto));
    }

    @DeleteMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Boolean> toggleMatchPreferences(@PathVariable Long id) {
        boolean toggleMatchPreferences = matchPreferencesService.toggleMatchPreferences(id);
        if (!toggleMatchPreferences) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    //@PostMapping
    //updateRithmAlgorithm
    //Input userCreateDto y matchPreferencesCreateDto
    //output lista de matchReadDto.
}
