package com.c1632mjava.c1632mjava.Application.Validations;

import com.c1632mjava.c1632mjava.Domain.Dtos.AuthResponse;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Esta clase debe pasarse a la capa de Domain, sección servicios
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Integrar oauth 2, UserRegister debe contenter la validación por oauth2
    public AuthResponse login(UserCreateDto data) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
        UserDetails user = userRepository.findByEmail(data.email()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Transactional
    public AuthResponse register(UserCreateDto data) {

        Optional.ofNullable(userRepository.findByEmail(data.email())
                .orElseThrow(() -> new RuntimeException("User already exists")));

        // Se debe incluir los demás atributos para crear el usuario
        User user = User.builder()
                .email(data.email())
                .password(passwordEncoder.encode(data.password()))
                .name(data.name())
                .birthdate(data.birthdate()) // dato a pedir
                .photo(data.photo())
                .gender(data.gender()) // dato a pedir
                .pronouns(data.pronouns()) // dato a pedir
                .description(data.description()) // dato a pedir
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
