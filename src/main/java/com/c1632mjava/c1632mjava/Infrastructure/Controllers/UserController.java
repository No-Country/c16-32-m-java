package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Application.Validations.AuthService;
import com.c1632mjava.c1632mjava.Domain.Dtos.ArtistDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.AuthResponse;
import com.c1632mjava.c1632mjava.Domain.Dtos.GenreDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserUpdateDto;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid
                                                    UserCreateDto userCreateDto,
                                                     @RequestParam (name ="userName") String name,
                                                     @RequestParam (name ="email") String email){
        //UserReadDto result = userService.registerUser(userCreateDto);
        try{
            UserCreateDto mergedUser = userCreateDto.withName(name).withEmail(email);
            return ResponseEntity.ok(authService.register(mergedUser));
        }
        catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        //return ResponseEntity.ok(result);
    }

    @PostMapping("/likedartists/{userId}")
    @Transactional
    public ResponseEntity<UserReadDto> addLikedArtistToUser(@PathVariable Long userId,
                                                    @RequestBody @Valid List<ArtistDto> artistDtoList){
        return ResponseEntity.ok(userService.addLikedArtistToUser(artistDtoList, userId));
    }

    @PostMapping("/likedgenres/{userId}")
    @Transactional
    public ResponseEntity<UserReadDto> addLikedGenreToUser(@PathVariable Long userId,
                                                    @RequestBody @Valid List<GenreDto> genreDtoList){
        return ResponseEntity.ok(userService.addLikedGenreToUser(genreDtoList, userId));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserReadDto>> findUserList (@PageableDefault(size = 10)
                                                           Pageable paging) {
        return ResponseEntity.ok(userService.findAllUsers(true, paging));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserReadDto> findById (@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserReadDto> findByEmail (@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PutMapping("/id/{id}")
    @Transactional
    public ResponseEntity<UserReadDto> updateUser (@RequestBody @Valid
                                                   UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.updateUser(userUpdateDto));
    }

    @DeleteMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Boolean> toggleUser(@PathVariable Long id) {
        boolean toggledUser = userService.toggleUser(id);
        if (!toggledUser) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{banningId}/ban/{matchId}")
    @Transactional
    ResponseEntity<Boolean> banUser (@PathVariable Long banningId,
                                     @PathVariable Long matchId){
        boolean result = userService.banUser(banningId, matchId);
        if (result) { return ResponseEntity.noContent().build(); }
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/bannedlist/{userId}")
    ResponseEntity<List<UserReadDto>> readAllBannedByUserId(@PathVariable Long userId){
        List<UserReadDto> bannedList = userService.findAllBannedByUserId(userId);
        return ResponseEntity.ok(bannedList);
    }

    @PutMapping("/{banningId}/unban/{unbannedUserId}")
    @Transactional
    ResponseEntity<Boolean> unbanUser (@PathVariable Long banningId,
                                     @PathVariable Long unbannedUserId) {
        boolean result = userService.unbanUser(banningId, unbannedUserId);
        if (result) { return ResponseEntity.ok().build(); }
        else return ResponseEntity.badRequest().build();
    }
}
