package com.example.chat.controller;

import com.example.chat.dto.ChatMessageDto;
import com.example.chat.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebSocketController {

    @Autowired
    private ChatService chatService;


    @MessageMapping("/send/{chatroomSeq}/{userId}")
    @SendTo("/sub/chatroom/{chatroomSeq}/{userId}")
    public ChatMessageDto sendMessage(@DestinationVariable String chatroomSeq,@DestinationVariable String userId, @Payload ChatMessageDto message) {

        return message;
    }


    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessageDto receiveMessage(ChatMessageDto messageDto) {
        chatService.saveMessage(String.valueOf(messageDto.getChatroomSeq()), messageDto);
        return messageDto;
    }

    @GetMapping("/api/chatroom/{chatroomSeq}/messages")
    public List<ChatMessageDto> getMessages(@PathVariable String chatroomSeq) {
        return chatService.getMessages(chatroomSeq);
    }


}