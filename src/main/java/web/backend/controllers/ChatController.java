package web.backend.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import web.backend.util.DTO.MessageDTO;

@RestController
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public MessageDTO receivePublicMessage(@Payload MessageDTO message) {
        return message;
    }

    @MessageMapping("/private-message")
    public MessageDTO receivePrivateMessage(@Payload MessageDTO message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        return message;
    }
}
