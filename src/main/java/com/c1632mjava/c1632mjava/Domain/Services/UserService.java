package com.c1632mjava.c1632mjava.Domain.Services;

import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface UserService {
    UserReadDto registerUser(UserCreateDto userCreateDto);
    Page<UserReadDto>  findAll(boolean active, Pageable paging);
    UserReadDto findUserById(Long id) throws EntityNotFoundException;
    UserReadDto findUserByEmail(String email) throws EntityNotFoundException;
    UserReadDto updateUser(UserUpdateDto userUpdateDto) throws EntityNotFoundException;
    Boolean toggleUser(Long id) throws EntityNotFoundException;
}
