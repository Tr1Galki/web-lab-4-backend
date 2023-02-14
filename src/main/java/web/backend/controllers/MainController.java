package web.backend.controllers;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    //TODO: ничего не менял в этом контроллере
    Logger logger = Logger.getLogger(MainController.class);

    final RabbitTemplate template;

    public MainController(RabbitTemplate template) {
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
        logger.info("Emit as error");
        template.convertAndSend("error", "Error");
        return "Emit as error";
    }

    @RequestMapping("/emit/info")
    @ResponseBody
    String info() {
        logger.info("Emit as info");
        template.convertAndSend("info", "Info");
        return "Emit as info";
    }

    @RequestMapping("/emit/warning")
    @ResponseBody
    String warning() {
        logger.info("Emit as warning");
        template.convertAndSend("warning", "Warning");
        return "Emit as warning";
    }
}
