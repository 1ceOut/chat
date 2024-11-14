# 🥶 냉장고의 모든 비밀 - Real-time Chat Feature

## 📌 Introduction

**냉장고의 모든 비밀** 프로젝트는 사용자가 냉장고를 공동 관리하며, 실시간으로 소통할 수 있는 환경을 제공합니다. 이 중 **실시간 채팅 기능**은 사용자 간 원활한 의사소통을 지원하여 공동 관리의 편의성을 높이는 핵심 기능입니다.

---

## ⚙️ 기술 스택

| 기술        | 설명                                      |
| ----------- | ----------------------------------------- |
| **React**   | 사용자 인터페이스를 위한 프론트엔드 프레임워크 |
| **Redis**   | 메시지 브로커 및 데이터 캐싱              |
| **STOMP**   | 실시간 메시징 프로토콜                    |
| **SockJS**  | WebSocket의 폴백을 지원하는 라이브러리    |

---

## 🚀 주요 기능

### 1. 실시간 채팅
- 사용자 간 **실시간 메시지 송수신** 지원
- 새로운 메시지가 도착하면 **자동으로 갱신**되어 표시

### 2. 채팅방 관리
- **여러 채팅방** 생성 및 참여
- 각 채팅방에 **개별 메시지 기록** 유지

### 3. 메시지 알림
- 새로운 메시지 수신 시 **알림 기능** 제공

---

## 🛠️ 구현 상세

### 1. 프론트엔드 (React)
- `SockJS`와 `STOMP`를 사용하여 WebSocket 연결을 관리
- 메시지 전송 및 수신 시 **Redux**를 활용해 상태 관리

```javascript
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

const socket = new SockJS('https://your-backend-url/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
  console.log('Connected: ' + frame);

  stompClient.subscribe('/topic/chat', (message) => {
    const newMessage = JSON.parse(message.body);
    dispatch(addMessage(newMessage)); // Redux action to store messages
  });
});

export const sendMessage = (chatMessage) => {
  stompClient.send('/app/chat', {}, JSON.stringify(chatMessage));
};
