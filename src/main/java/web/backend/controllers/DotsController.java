package web.backend.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import web.backend.util.DTO.AddDotDTO;
import web.backend.util.DTO.GetDotsDTO;
import web.backend.util.DTO.SendDotsDTO;

@RestController
public class DotsController {

    private final RabbitTemplate rabbitTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public DotsController(@Qualifier("routeRabbitTemplate") RabbitTemplate rabbitTemplate,
                          SimpMessagingTemplate simpMessagingTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/add-dot")
    public void receiveAddDotMessage(@Payload AddDotDTO addDotDTO) {
        rabbitTemplate.convertAndSend("add-dots", addDotDTO.toJsonString());
    }

    @MessageMapping("/get-dots")
    public void receiveGetAllDotsMessage(@Payload GetDotsDTO getDotsDTO) {
        rabbitTemplate.convertAndSend("get-dots", getDotsDTO.toJsonString());
    }

    public void sendDots(@Payload SendDotsDTO dto) {
        simpMessagingTemplate.convertAndSendToUser(dto.getReceiverName(), "/dots", dto);
    }

    @RabbitListener(queues = "back-queue")
    private void processBackQueue(String message) {
        sendDots(new SendDotsDTO(message));
    }
}
