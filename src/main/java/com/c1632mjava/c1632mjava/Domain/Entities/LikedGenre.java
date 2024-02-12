package com.c1632mjava.c1632mjava.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name="liked_genres", uniqueConstraints =
@UniqueConstraint(columnNames = {"users_id", "genres_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LikedGenre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likedGenresId;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "genres_id")
    private Genre genre;

    private boolean active;

}
