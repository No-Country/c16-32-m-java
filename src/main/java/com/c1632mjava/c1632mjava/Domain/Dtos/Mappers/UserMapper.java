package com.c1632mjava.c1632mjava.Domain.Dtos.Mappers;

import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
        UserReadDto convertUserToRead(User user);
        User convertCreateToUser(UserCreateDto userCreateDto);
     }
