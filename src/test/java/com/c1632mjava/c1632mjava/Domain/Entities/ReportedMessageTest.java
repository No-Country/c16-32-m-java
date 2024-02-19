package com.c1632mjava.c1632mjava.Domain.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportedMessageTest {
    @Test
    void noArgsConstructor() {
        //GIVEN AND WHEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //THEN
        assertNotNull(reportedMessage);
    }

    @Test
    void AllArgsConstructor() {
        //GIVEN AND WHEN
        ReportedMessage reportedMessage = new ReportedMessage(1L, "Me sentí ofendido.", null, null, null, null, null);

        //THEN
        assertNotNull(reportedMessage);
    }

    @Test
    void getReportedMessageId() {
    }

    @Test
    void getMessage() {
    }

    @Test
    void getDate() {
    }

    @Test
    void getReviewed() {
    }

    @Test
    void getChat() {
    }

    @Test
    void getSender() {
    }

    @Test
    void getReceiver() {
    }

    @Test
    void setReportedMessageId() {
    }

    @Test
    void setMessage() {
    }

    @Test
    void setDate() {
    }

    @Test
    void setReviewed() {
    }

    @Test
    void setChat() {
    }

    @Test
    void setSender() {
    }

    @Test
    void setReceiver() {
    }
}