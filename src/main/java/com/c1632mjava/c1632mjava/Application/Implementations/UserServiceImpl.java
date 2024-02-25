package com.c1632mjava.c1632mjava.Application.Implementations;

import com.c1632mjava.c1632mjava.Domain.Dtos.*;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.*;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.*;
import com.c1632mjava.c1632mjava.Domain.Entities.*;
import com.c1632mjava.c1632mjava.Domain.Repositories.*;
import com.c1632mjava.c1632mjava.Domain.Services.MatchPreferencesService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.MatchNotFoundException;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final UserMapper userMapper;
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final MatchPreferencesService matchPreferencesService;
    private final MatchRepository matchRepository;


    @Override
    public Page<UserReadDto> findAllUsers(boolean active, Pageable paging) {
        return userRepository.findAllByActive(active, paging).
                map(userMapper::convertUserToRead);
    }

    @Override
    public UserReadDto findUserById(Long id)
            throws UserNotFoundException {
        User user = this.userRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException(id));
        return userMapper.convertUserToRead(user);
    }

    @Override
    public UserReadDto findUserByEmail(String email)
            throws UserNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow( () -> new UserNotFoundException(email));
        return userMapper.convertUserToRead(user);
    }

    @Override
    public UserReadDto updateUser(UserUpdateDto userUpdateDto)
            throws UserNotFoundException {
        User user = this.userRepository.findById(userUpdateDto.userId())
                .orElseThrow(() -> new UserNotFoundException(userUpdateDto.userId()));

        if (user.isActive()) {
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
    public Boolean toggleUser(Long id)
            throws UserNotFoundException {
        User userToToggle = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userToToggle.setActive(!userToToggle.isActive());
        this.userRepository.save(userToToggle);
        matchPreferencesService.toggleMatchPreferences(id);
        return userToToggle.isActive();
    }

    @Override
    public UserReadDto addLikedArtistToUser(List<ArtistDto> artistDtoList, Long userId)
            throws UserNotFoundException {
        ArrayList<Artist> editedArtistList = new ArrayList<>();
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new UserNotFoundException(userId));
        user.setArtists(null);

        for (ArtistDto artistDto : artistDtoList) {
            Artist artist = artistMapper.convertDtoToArtist(artistDto);
            if (!artistRepository.existsById(artist.getArtistId())) {
                artist = artistRepository.save(artist);
            }
            editedArtistList.add(artist);
        }

        user.setArtists(editedArtistList);
        user = userRepository.save(user);
        return userMapper.convertUserToRead(user);
    }

    @Override
    public UserReadDto addLikedGenreToUser(List<GenreDto> genreDtoList, Long userId)
            throws UserNotFoundException {
        ArrayList<Genre> editedGenreList = new ArrayList<>();
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new UserNotFoundException(userId));
        user.setGenres(null);

        for (GenreDto genreDto : genreDtoList) {
            Genre genre = genreMapper.convertDtoToGenre(genreDto);
            if (!genreRepository.existsById(genre.getGenreId())) {
                genre = genreRepository.save(genre);
            }
            editedGenreList.add(genre);
        }

        user.setGenres(editedGenreList);
        user = userRepository.save(user);
        return userMapper.convertUserToRead(user);
    }

    @Override
    public boolean banUser (Long banningUserId, Long matchId)
            throws MatchNotFoundException, UserNotFoundException{
        UserReadDto loggedUser = findUserById(banningUserId); //Reemplazar luego por el user logueado.
        Match matchToBan = matchRepository.findById(matchId)
                .orElseThrow( () -> new MatchNotFoundException(matchId));
        Long bannedUserId = 0L;
        if (matchToBan.getUser1().getUserId().equals(loggedUser.userId())) {
            bannedUserId = matchToBan.getUser2().getUserId();
        }
        if (matchToBan.getUser2().getUserId().equals(loggedUser.userId())) {
            bannedUserId = matchToBan.getUser1().getUserId();
        }
        //TODO. Estas validaciones deberían hacerse aparte y sólo llamarse acá, para mejorar lectura.
        //En concreto, los casos falsos son: 1° el user logged no corresponde a ninguno de los user matched.
        //2° El match ya se encuentra inactivo (fue bloqueado antes).
        if (!matchToBan.getUser1().getUserId().equals(loggedUser.userId())
                && !matchToBan.getUser2().getUserId().equals(loggedUser.userId())) {
            throw new UserNotFoundException(loggedUser.userId());
        }
        if (!matchToBan.getActive()) {
            throw new MatchNotFoundException(matchId);
        }

        User userToUpdate = userMapper.convertReadToUser(loggedUser);

        if (!userToUpdate.getBannedUsers().contains(bannedUserId)) {
            matchToBan.setActive(false);
            matchRepository.save(matchToBan);
            userToUpdate.getBannedUsers().add(bannedUserId);
            userRepository.save(userToUpdate);
        }
        return true;
    }

    @Override
    public List <UserReadDto> findAllBannedByUserId(Long id){
        List <UserReadDto> bannedUsers = new ArrayList<>();
        UserReadDto loggedUser = findUserById(id);
        List <Long> bannedListIds = loggedUser.bannedUsers();
        for (Long bannedUserId : bannedListIds){
            UserReadDto bannedUser = findUserById(bannedUserId);
            bannedUsers.add(bannedUser);
        }
        return bannedUsers;
    }

    @Override
    public boolean unbanUser (Long loggedUserId, Long unbanUserId) {
        UserReadDto loggedUser = findUserById(loggedUserId); //Reemplazar luego por el user logueado.
        User userToUpdate = userMapper.convertReadToUser(loggedUser);
        if (!userRepository.existsById(unbanUserId) ||
                !userToUpdate.getBannedUsers().contains(unbanUserId)) {
            throw new UserNotFoundException(unbanUserId);
        }
            userToUpdate.getBannedUsers().remove(unbanUserId);
            userRepository.save(userToUpdate);
            return true;
        }

    }

