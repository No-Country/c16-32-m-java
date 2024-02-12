package com.c1632mjava.c1632mjava.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="banned")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banned implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banned_id")
    private Long bannedId;

    @Column(name = "date_of_match")
    private LocalDateTime dateOfMatch;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
