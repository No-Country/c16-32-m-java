package com.c1632mjava.c1632mjava.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "matches", uniqueConstraints =
@UniqueConstraint(columnNames = {"user_id_1", "user_id_2"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "compatibility_percentage")
    private Float compatibilityPercentage;

    @Column(name = "date_of_match")
    private LocalDateTime dateOfMatch;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id_1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id_2")
    private User user2;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "matches")
    private List<Chat> chats;
}
