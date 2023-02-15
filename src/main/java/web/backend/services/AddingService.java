package web.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddingService {
    //TODO: реализовать

    private final RabbitTemplate template;

    public AddingService(@Qualifier("backRabbitTemplate") RabbitTemplate template) {
        this.template = template;
    }


    @RabbitListener(queues = "adding-query")
    public void receive(String message) {
        System.out.println("accepted on worker 1: " + message);
        send(message);
    }

    private void send(String message) {
        System.out.println("send back: " + message);
        template.convertAndSend("queue1", message);
    }


}
