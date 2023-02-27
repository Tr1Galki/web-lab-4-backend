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
import web.backend.util.DTO.MessageDTO;

@RestController
public class MainController {

    //TODO: ничего не менял в этом контроллере

    private final RabbitTemplate rabbitTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper mapper;

    public MainController(@Qualifier("routeRabbitTemplate") RabbitTemplate rabbitTemplate,
                          SimpMessagingTemplate simpMessagingTemplate,
                          ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.mapper = mapper;
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

    @MessageMapping("/add")
    public void receiveDotsMessage(@Payload AddDotDTO addDotDTO) {
        //TODO: в addDotDTO нет имени владельца и прочего...
        rabbitTemplate.convertAndSend("add", addDotDTO);
    }

//    @RequestMapping("/add-with-share")
//    @ResponseBody
//    private String error() {
//        System.out.println("add-with-share");
//        rabbitTemplate.convertAndSend("add-with-share", "Add-with-share");
//        return "Completed";
//    }
//
//    @RequestMapping("/addDot")
//    @ResponseBody
//    private String info(@RequestParam("x") Double x,
//                        @RequestParam("y") Double y,
//                        @RequestParam("r") Double r,
//                        @RequestParam("date") Integer date,
//                        @RequestParam("creator") String creator,
//                        @RequestParam("owner") String owner) throws JsonProcessingException {
//        System.out.println("get dot");
//        DotEntity dot = new DotEntity(x, y, r, date, creator, owner);
//        String message = mapper.writeValueAsString(dot);
//
//        rabbitTemplate.convertAndSend("add", message);
//
//        return "Added";
//    }
//
//    @RequestMapping("/share")
//    @ResponseBody
//    private String warning() {
//        System.out.println("share");
//        rabbitTemplate.convertAndSend("share", "Share");
//        return "Share";
//    }

    @RabbitListener(queues = "back-queue")
    private void processQueue1(String message) {
        System.out.println("Received from back-queue: " + message);
    }
}
