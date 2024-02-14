package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.UserMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.UserRepository;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final UserMapper userMapper;

    @Override
    public UserReadDto registerUser(UserCreateDto userCreateDto) {
        User user = userMapper.convertCreateToUser(userCreateDto);
        /*TODO. Password logistics, such as encoder, etc*/
        userRepository.save(user);
        return userMapper.convertUserToRead(user);
    }

    @Override
    public Page<UserReadDto> findAll(boolean active, Pageable paging) {
        return this.userRepository.findAllByActive(active, paging).
                map(userMapper::convertUserToRead);
    }

    @Override
    public UserReadDto findUserById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return userMapper.convertUserToRead(user);
    }

    @Override
    public UserReadDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
        return userMapper.convertUserToRead(user);
    }

    @Override
    public UserReadDto updateUser(UserUpdateDto userUpdateDto) {
        User user = this.userRepository.findById(userUpdateDto.userId())
                .orElseThrow(EntityNotFoundException::new);

        if (user.isActive()){
            if (userUpdateDto.name() != null) {
                user.setName(userUpdateDto.name());
            }

            /*TODO. UPDATE PASSWORD LOGIC MISSING! REQUIRES ENCODING!*/

            if (userUpdateDto.birthdate() != null) {
                user.setBirthdate(userUpdateDto.birthdate());
            }

            if (userUpdateDto.photo() != null) {
                user.setPhoto(userUpdateDto.photo());
            }

            if (userUpdateDto.gender() != null) {
                user.setGender(userUpdateDto.gender());
            }

            if (userUpdateDto.pronouns() != null) {
                user.setPronouns(userUpdateDto.pronouns());
            }

            if (userUpdateDto.description() != null) {
                user.setDescription(userUpdateDto.description());
            }

            if (userUpdateDto.socialBattery() != null) {
                user.setSocialBattery(userUpdateDto.socialBattery());
            }

            if (userUpdateDto.currentSong() != null) {
                user.setCurrentSong(userUpdateDto.currentSong());
            }

            this.userRepository.save(user);
        }
        return userMapper.convertUserToRead(user);
    }

    @Override
    public Boolean toggleUser(Long id) throws EntityNotFoundException {
        User userToToggle = this.userRepository.findById(id).orElse(null);
        userToToggle.setActive(!userToToggle.isActive());
        this.userRepository.save(userToToggle);
        return userToToggle.isActive();
    }

    /*metodo registro*/
    @Override
    public User save(UserCreateDto userCreateDto) {
        User user=new User();
        user.getName();
        user.getPronouns();
        user.getGender();
        user.getBirthdate();
        user.getDescription();

        return userRepository.save(user);
    }
}
