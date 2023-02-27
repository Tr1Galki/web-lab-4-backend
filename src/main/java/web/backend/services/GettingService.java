package web.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.backend.repositories.DotsRepository;

@Service
public class GettingService {

    private final RabbitTemplate template;
    private final DotsRepository repository;

    public GettingService(@Qualifier("backRabbitTemplate") RabbitTemplate template, DotsRepository repository) {
        this.template = template;
        this.repository = repository;
    }

    private void handlingMessage(String data) {
        repository.getDotsByOwner(data);
        send(data);
    }

    @RabbitListener(queues = "adding-query")
    private void receive(String data) {
        handlingMessage(data);
    }

    private void send(String data) {
        System.out.println("send back: " + data);
        template.convertAndSend("back-queue", data);
    }
}
