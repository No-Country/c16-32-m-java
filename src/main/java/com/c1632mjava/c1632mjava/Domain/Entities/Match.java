package com.c1632mjava.c1632mjava.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    @OneToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
