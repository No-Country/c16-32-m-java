package com.c1632mjava.c1632mjava.Domain.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ReportedMessageTest {
    private Long reportedMessageId;
    private String message;
    private LocalDateTime date;
    private Boolean reviewed;
    private Chat chat;
    private User sender;
    private User receiver;

    @BeforeEach
    void setUp() {
        this.reportedMessageId = 1L;
        this.message = "Me sentí ofendido.";
        this.date = LocalDateTime.now();
        this.reviewed = Boolean.FALSE;
        this.chat = new Chat();
        this.sender = new User();
        this.receiver = new User();
    }

    @Test
    void noArgsConstructor() {
        //GIVEN AND WHEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //THEN
        assertNotNull(reportedMessage);
        assertNull(reportedMessage.getReportedMessageId());
        assertNull(reportedMessage.getMessage());
        assertNull(reportedMessage.getChat());
        assertNull(reportedMessage.getDate());
        assertNull(reportedMessage.getSender());
        assertNull(reportedMessage.getReceiver());
        assertNull(reportedMessage.getReviewed());
    }

    @Test
    void AllArgsConstructor() {
        //GIVEN AND WHEN
        ReportedMessage reportedMessage = new ReportedMessage
                (this.reportedMessageId, this.message, this.date, this.reviewed, this.chat, this.sender, this.receiver);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.reportedMessageId, reportedMessage.getReportedMessageId());
        assertEquals(this.message, reportedMessage.getMessage());
        assertEquals(this.date, reportedMessage.getDate());
        assertEquals(this.reviewed, reportedMessage.getReviewed());
        assertEquals(this.chat, reportedMessage.getChat());
        assertEquals(this.sender, reportedMessage.getSender());
        assertEquals(this.receiver, reportedMessage.getReceiver());
    }

    @Test
    void setReportedMessageId() {
        //GIVEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //WHEN
        reportedMessage.setReportedMessageId(this.reportedMessageId);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.reportedMessageId, reportedMessage.getReportedMessageId());
    }

    @Test
    void setMessage() {
        //GIVEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //WHEN
        reportedMessage.setMessage(this.message);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.message, reportedMessage.getMessage());
    }

    @Test
    void setDate() {
        //GIVEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //WHEN
        reportedMessage.setDate(this.date);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.date, reportedMessage.getDate());
    }

    @Test
    void setReviewed() {
        //GIVEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //WHEN
        reportedMessage.setReviewed(this.reviewed);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.reviewed, reportedMessage.getReviewed());
    }

    @Test
    void setChat() {
        //GIVEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //WHEN
        reportedMessage.setChat(this.chat);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.chat, reportedMessage.getChat());
    }

    @Test
    void setSender() {
        //GIVEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //WHEN
        reportedMessage.setSender(this.sender);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.sender, reportedMessage.getSender());
    }

    @Test
    void setReceiver() {
        //GIVEN
        ReportedMessage reportedMessage = new ReportedMessage();

        //WHEN
        reportedMessage.setReceiver(this.receiver);

        //THEN
        assertNotNull(reportedMessage);
        assertEquals(this.receiver, reportedMessage.getReceiver());
    }
}