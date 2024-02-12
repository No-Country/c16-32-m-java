package com.c1632mjava.c1632mjava.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="chats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "last_message")
    private String lastMessage;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "previous_messages")
    private ArrayList<String> previousMessages;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "matches_chats",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Match> matches;
}
