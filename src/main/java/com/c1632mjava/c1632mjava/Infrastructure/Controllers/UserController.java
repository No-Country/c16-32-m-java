package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.ArtistDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.GenreDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserUpdateDto;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserReadDto> registerUser(@RequestBody @Valid
                                                    UserCreateDto userCreateDto){
        UserReadDto result = userService.registerUser(userCreateDto);
        return ResponseEntity.ok(result);
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
        return ResponseEntity.ok(userService.findAll(true, paging));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserReadDto> findById (@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserReadDto> findByEmail (@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserReadDto> updateUser (@RequestBody @Valid
                                                   UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.updateUser(userUpdateDto));
    }

    @PutMapping("/id/{id}")
    @Transactional
    public ResponseEntity<Boolean> toggleUser(@PathVariable Long id) {
        boolean toggledUser = userService.toggleUser(id);
        if (!toggledUser) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
