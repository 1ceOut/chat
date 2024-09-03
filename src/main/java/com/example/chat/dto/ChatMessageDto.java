package com.example.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private String sender;
    private String message;
    private String chatroomSeq; // 채팅방 식별자
    private Long senderSeq;
    private String timestamp;
    private String datestamp;
    private String userProfile;

}
