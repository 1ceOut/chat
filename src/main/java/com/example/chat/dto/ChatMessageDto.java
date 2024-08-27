package com.example.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private Long id;
    private String sender;
    private String message;
    private String chatroomSeq; // 채팅방 식별자
    private Long senderSeq;

}