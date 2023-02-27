package web.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import web.backend.util.DTO.AddDotDTO;
import web.backend.util.DTO.GetDotsDTO;
import web.backend.util.DTO.MessageDTO;
import web.backend.util.DTO.SendDotsDTO;

@RestController
public class    MainController {

    //TODO: ничего не менял в этом контроллере

    private final RabbitTemplate rabbitTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public MainController(@Qualifier("routeRabbitTemplate") RabbitTemplate rabbitTemplate,
                          SimpMessagingTemplate simpMessagingTemplate,
                          ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

//    @RequestMapping("/")
//    @ResponseBody
//    private String home() {
//        return "Empty mapping";
//    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public MessageDTO receivePublicMessage(@Payload MessageDTO message) {
        return message;
    }

    @MessageMapping("/private-message")
    public MessageDTO receivePrivateMessage(@Payload MessageDTO message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        System.out.println(message.toString());
        return message;
    }

    @MessageMapping("/add-dot")
    public void receiveAddDotMessage(@Payload AddDotDTO addDotDTO) {
        //TODO: в addDotDTO нет имени владельца и прочего...
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
