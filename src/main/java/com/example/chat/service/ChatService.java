package com.example.chat.service;


import com.example.chat.dto.ChatMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    public ChatMessageDto saveMessage(ChatMessageDto messageDto) {
        return new ChatMessageDto(
                messageDto.getSender(),
                messageDto.getMessage(),
                messageDto.getChatroomSeq(),
                messageDto.getSenderSeq(),
                messageDto.getTimestamp(),
                messageDto.getDatestamp(),
                messageDto.getUserProfile()
        );
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper; // Jackson ObjectMapper

    private static final String CHAT_ROOM_PREFIX = "chatroom:";

    public void saveMessage(String chatroomSeq, ChatMessageDto messageDto) {
        String key = CHAT_ROOM_PREFIX + chatroomSeq;
        try {
            String messageJson = objectMapper.writeValueAsString(messageDto);
            redisTemplate.opsForList().rightPush(key, messageJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ChatMessageDto> getMessages(String chatroomSeq) {
        String key = CHAT_ROOM_PREFIX + chatroomSeq;
        List<String> messagesJson = redisTemplate.opsForList().range(key, 0, -1);

        if (messagesJson == null) {
            return List.of();
        }

        return messagesJson.stream()
                .map(json -> {
                    try {
                        return objectMapper.readValue(json, ChatMessageDto.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(messageDto -> messageDto != null)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/api/chatroom/{chatroomSeq}/last-message")
    public ChatMessageDto getLastMessage(@PathVariable String chatroomSeq) {
        return chatService.getLastMessage(chatroomSeq);
    }

}
