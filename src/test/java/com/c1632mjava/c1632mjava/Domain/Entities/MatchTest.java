package com.c1632mjava.c1632mjava.Domain.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private Long matchId;
    private Float compatibilityPercentage;
    private LocalDateTime dateOfMatch;
    private Boolean active;
    private User user1;
    private User user2;
    private Chat chat;

    @BeforeEach
    void setUp() {
        this.matchId = 1L;
        this.compatibilityPercentage = 67.9F;
        this.dateOfMatch = LocalDateTime.now();
        this.active = Boolean.TRUE;
        this.user1 = new User();
        this.user2 = new User();
        this.chat = new Chat();
    }

    @Test
    void noArgsConstructor() {
        //GIVEN AND WHEN
        Match match = new Match();

        //THEN
        assertNotNull(match);
        assertNull(match.getMatchId());
        assertNull(match.getDateOfMatch());
        assertNull(match.getChat());
        assertNull(match.getActive());
        assertNull(match.getCompatibilityPercentage());
        assertNull(match.getUser1());
        assertNull(match.getUser2());
    }

    @Test
    void AllArgsConstructor() {
        //GIVEN AND WHEN
        Match match = new Match
                (this.matchId, this.compatibilityPercentage, this.dateOfMatch, this.active, this.user1, this.user2, this.chat);

        //THEN
        assertNotNull(match);
        assertEquals(this.matchId, match.getMatchId());
        assertEquals(this.compatibilityPercentage, match.getCompatibilityPercentage());
        assertEquals(this.dateOfMatch, match.getDateOfMatch());
        assertEquals(this.active, match.getActive());
        assertEquals(this.user1, match.getUser1());
        assertEquals(this.user2, match.getUser2());
        assertEquals(this.chat, match.getChat());
    }

    @Test
    void setMatchId() {
        //GIVEN
        Match match = new Match();

        //WHEN
        match.setMatchId(this.matchId);

        //THEN
        assertNotNull(match);
        assertEquals(this.matchId, match.getMatchId());
    }

    @Test
    void setCompatibilityPercentage() {
        //GIVEN
        Match match = new Match();

        //WHEN
        match.setCompatibilityPercentage(this.compatibilityPercentage);

        //THEN
        assertNotNull(match);
        assertEquals(this.compatibilityPercentage, match.getCompatibilityPercentage());
    }

    @Test
    void setDateOfMatch() {

        //GIVEN
        Match match = new Match();

        //WHEN
        match.setDateOfMatch(this.dateOfMatch);

        //THEN
        assertNotNull(match);
        assertEquals(this.dateOfMatch, match.getDateOfMatch());
    }

    @Test
    void setActive() {
        //GIVEN
        Match match = new Match();

        //WHEN
        match.setActive(this.active);

        //THEN
        assertNotNull(match);
        assertEquals(this.active, match.getActive());
    }

    @Test
    void setUser1() {
        //GIVEN
        Match match = new Match();

        //WHEN
        match.setUser1(this.user1);

        //THEN
        assertNotNull(match);
        assertEquals(this.user1, match.getUser1());
    }

    @Test
    void setUser2() {
        //GIVEN
        Match match = new Match();

        //WHEN
        match.setUser2(this.user2);

        //THEN
        assertNotNull(match);
        assertEquals(this.user2, match.getUser2());
    }

    @Test
    void setChat() {
        //GIVEN
        Match match = new Match();

        //WHEN
        match.setChat(this.chat);

        //THEN
        assertNotNull(match);
        assertEquals(this.chat, match.getChat());
    }
}