package web.backend.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import web.backend.util.DotEntity;

@RestController
public class MainController {

    //TODO: ничего не менял в этом контроллере

    private final RabbitTemplate template;

    public MainController(@Qualifier("routeRabbitTemplate") RabbitTemplate template) {
        this.template = template;
    }

    @RequestMapping("/")
    @ResponseBody
    private String home() {
        return "Empty mapping";
    }

    @RequestMapping("/add-with-share")
    @ResponseBody
    private String error() {
        System.out.println("add-with-share");
        template.convertAndSend("add-with-share", "Add-with-share");
        return "Completed";
    }

    @RequestMapping("/addDot")
    @ResponseBody
    private String info(@RequestParam("x") Double x,
                        @RequestParam("y") Double y,
                        @RequestParam("r") Double r,
                        @RequestParam("date") Integer date,
                        @RequestParam("creator") String creator,
                        @RequestParam("owner") String owner) {
        System.out.println("get dot");
        DotEntity dot = new DotEntity(x, y, r, date, creator, owner);
        template.convertAndSend("add", dot);
        return "Added";
    }

    @RequestMapping("/share")
    @ResponseBody
    private String warning() {
        System.out.println("share");
        template.convertAndSend("share", "Share");
        return "Share";
    }

    @RabbitListener(queues = "back-queue")
    private void processQueue1(String message) {
        System.out.println("Received from back-queue: " + message);
    }
}
