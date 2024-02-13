package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.ArtistDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.GenreDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    UserReadDto registerUser(UserCreateDto userCreateDto);
    Page<UserReadDto>  findAll(boolean active, Pageable paging);
    UserReadDto findUserById(Long id) throws EntityNotFoundException;
    UserReadDto findUserByEmail(String email) throws EntityNotFoundException;
    UserReadDto updateUser(UserUpdateDto userUpdateDto) throws EntityNotFoundException;
    Boolean toggleUser(Long id) throws EntityNotFoundException;
    UserReadDto addLikedArtistToUser(List<ArtistDto> artistDtoList, Long userId);
    UserReadDto addLikedGenreToUser(List<GenreDto> genreDtoList, Long userId);

}
