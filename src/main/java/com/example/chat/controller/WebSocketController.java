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

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    // 메시지를 특정 채팅방으로 전송하고, 같은 채팅방을 구독하는 클라이언트에게 메시지를 전달합니다.
//    @MessageMapping("/pub/message/{chatroomSeq}")
//    @SendTo("/sub/chatroom/{chatroomSeq}")
//    public ChatMessageDto sendMessage(@DestinationVariable String chatroomSeq, @Payload ChatMessageDto message) {
//        System.out.println("받아오는 메세지 : "+message);
//        chatService.saveMessage(chatroomSeq, message);
//        return message;
//    }

    @MessageMapping("/message")
    @SendTo("/topic/messages/{chatroomSeq}")
    public ChatMessageDto receiveMessage(ChatMessageDto messageDto) {
        chatService.saveMessage(String.valueOf(messageDto.getChatroomSeq()), messageDto);
        return messageDto;
    }

    @GetMapping("/api/chatroom/{chatroomSeq}/messages")
    public List<ChatMessageDto> getMessages(@PathVariable String chatroomSeq) {
        return chatService.getMessages(chatroomSeq);
    }

    @GetMapping("/api/chatroom/{chatroomSeq}/last-message")
    public ChatMessageDto getLastMessage(@PathVariable String chatroomSeq) {
        return chatService.getLastMessage(chatroomSeq);
    }

    // 공지사항 저장
    @PostMapping("/api/announcement/{chatroomSeq}")
    public void setAnnouncement(@PathVariable String chatroomSeq, @RequestBody String announcement) {
        chatService.saveAnnouncement(chatroomSeq, announcement);
    }

    // 공지사항 조회
    @GetMapping("/api/announcement/{chatroomSeq}")
    public String getAnnouncement(@PathVariable String chatroomSeq) {
        return chatService.getAnnouncement(chatroomSeq);
    }
}