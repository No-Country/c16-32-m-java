package com.c1632mjava.c1632mjava.Domain.Entities;

import com.c1632mjava.c1632mjava.Domain.Entities.Enums.Gender;
import com.c1632mjava.c1632mjava.Domain.Entities.Enums.SocialBattery;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> 38e00a1a9100af4032e05c5b3e190178c6cba873
=======
import java.util.ArrayList;
>>>>>>> a6828fe703a934cb57e363190eed89caf731f4b9

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String password;
    private LocalDateTime birthdate;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String pronouns;
    private String description;

    @Enumerated(EnumType.STRING)
    private SocialBattery socialBattery;

    private String currentSong;

    @ManyToMany (fetch = FetchType.EAGER)
    private ArrayList<Artist> Artists;

    @ManyToMany (fetch = FetchType.EAGER)
    private ArrayList<Genre> Genres;

    private ArrayList<Long> bannedUsers = new ArrayList<>();

    private boolean active = true;

    // UserDetails validations using JWT
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}