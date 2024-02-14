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
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable, UserDetails {

    @Getter
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
    private List<Artist> Artists;

    @ManyToMany (fetch = FetchType.EAGER)
    private List<Genre> Genres;

    private List <Long> bannedUsers;

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