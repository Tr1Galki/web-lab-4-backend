package web.backend.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    //TODO: ничего не менял в этом контроллере

    private final RabbitTemplate template;

    public MainController(@Qualifier("routeRabbitTemplate") RabbitTemplate template) {
        this.template = template;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Empty mapping";
    }

    @RequestMapping("/emit/error")
    @ResponseBody
    String error() {
        System.out.println("Emit as error");
        template.convertAndSend("error", "Error");
        return "Emit as error";
    }

    @RequestMapping("/emit/info")
    @ResponseBody
    String info() {
        System.out.println("Emit as info");
        template.convertAndSend("info", "Info");
        return "Emit as info";
    }

    @RequestMapping("/emit/warning")
    @ResponseBody
    String warning() {
        System.out.println("Emit as warning");
        template.convertAndSend("warning", "Warning");
        return "Emit as warning";
    }

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        System.out.println("Received from queue 1: " + message);
    }
}
