package com.c1632mjava.c1632mjava.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name="liked_artists", uniqueConstraints =
@UniqueConstraint(columnNames = {"users_id", "artists_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LikedArtist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likedArtistId;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "artists_id")
    private Artist artist;

    private boolean active;

}
