package com.c1632mjava.c1632mjava.Infrastructure.Controllers;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/chats")
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<ChatReadDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.chatService.findById(id));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Page<ChatReadDto>> findAllByUserId(@PageableDefault(size = 10) Pageable paging,
                                                             @PathVariable Long userId){
        return ResponseEntity.ok(this.chatService.findAllByUserId(userId, paging));
    }

    // Only for tests (development)
    @PostMapping
    public ResponseEntity<Chat> create(@RequestBody ChatCreateDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.chatService.create(dto));
    }
}
