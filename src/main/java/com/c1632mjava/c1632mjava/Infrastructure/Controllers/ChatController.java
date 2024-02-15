package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chats")
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<ChatReadDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.chatService.findById(id));
    }

    @GetMapping("/senders/{senderId}")
    public ResponseEntity<List<ChatReadDto>> findAllBySenderId(@PathVariable Long senderId){
        return ResponseEntity.ok(this.chatService.findAllBySenderId(senderId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.chatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
