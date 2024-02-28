package com.c1632mjava.c1632mjava.Infrastructure.Controllers;

import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatCreateDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Chat.ChatReadDto;
import com.c1632mjava.c1632mjava.Domain.Dtos.Mappers.ChatMapper;
import com.c1632mjava.c1632mjava.Domain.Dtos.User.UserReadDto;
import com.c1632mjava.c1632mjava.Domain.Entities.Chat;
import com.c1632mjava.c1632mjava.Domain.Entities.User;
import com.c1632mjava.c1632mjava.Domain.Services.ChatService;
import com.c1632mjava.c1632mjava.Domain.Services.UserService;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.ChatNotFoundException;
import com.c1632mjava.c1632mjava.Infrastructure.Errors.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@RequestMapping("/chats")
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMapper chatMapper;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ChatReadDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(this.chatService.findById(id));
    }

    @GetMapping("/senders/{senderId}")
    public ResponseEntity<Page<ChatReadDto>> findAllBySenderId(@PageableDefault(size = 10) Pageable paging,
                                                               @PathVariable Long senderId){
        return ResponseEntity.ok(this.chatService.findAllBySenderId(senderId, paging));
    }

    // Only for tests (development)
    @PostMapping
    public ResponseEntity<Chat> create(@RequestBody ChatCreateDto dto){
        Chat createdChat = chatService.create(dto);
        createdChat.getPreviousMessages().add(dto.lastMessage());
        chatService.save(dto);
        createdChat.setChatId(createdChat.getChatId());
        String destination = "/topic/chat" + createdChat.getChatId();
        messagingTemplate.convertAndSend(destination, createdChat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChat);
    }

    @MessageMapping("/chat/{id}")
    public void handleChatMessage(@DestinationVariable Long id, ChatCreateDto dto) {
        try {
            // Intenta encontrar el chat existente
            ChatReadDto chat = chatService.findById(id);
            // Verifica si el remitente coincide con el ID del remitente proporcionado en el mensaje
            if (!Objects.equals(chat.sender().userId(), dto.senderId())) {
                throw new ChatNotFoundException(id);
            }
            // Actualiza la información del chat con el nuevo mensaje, fecha, etc.
            ChatReadDto updatedChat = chat.withLastMessage(dto.lastMessage())
                    .withDate(LocalDateTime.now())
                    .withPreviousMessages(dto.previousMessages());
            // Guarda el chat actualizado
            chatService.save(chatMapper.convertReadToCreate(updatedChat));
            // Envía el mensaje actualizado a los suscriptores del chat
            messagingTemplate.convertAndSend("/topic/chat" + id, dto);
        } catch (ChatNotFoundException e) {
            // Si no se encuentra el chat, crea uno nuevo con el ID proporcionado
            UserReadDto sender = userService.findUserById(dto.senderId()); //
            UserReadDto receiver = userService.findUserById(dto.receiverId()); //
            // Crea un nuevo chat con los usuarios especificados
            ChatReadDto newChat = new ChatReadDto(id,
                    dto.lastMessage(),
                    LocalDateTime.now(),
                    new ArrayList<>(),
                    sender,
                    receiver);
            chatService.save(chatMapper.convertReadToCreate(newChat));
            // Envía el mensaje a los suscriptores del nuevo chat
            messagingTemplate.convertAndSend("/topic/chat" + id, dto);
        }
    }
}