package web.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.backend.repositories.DotsRepository;
import web.backend.repositories.UsersRepository;

@Service
public class SharingService {
    //TODO: реализовать

    final DotsRepository dotsRepository;
    final UsersRepository usersRepository;
    private final RabbitTemplate template;

    public SharingService(
            DotsRepository dotsRepository,
            UsersRepository usersRepository,
            @Qualifier("backRabbitTemplate") RabbitTemplate template) {
        this.dotsRepository = dotsRepository;
        this.usersRepository = usersRepository;
        this.template = template;
    }

    @RabbitListener(queues = "sharing-query")
    private void receive(String message) {
        System.out.println("accepted on worker 2: " + message);
        send(message);
    }

    private void send(String message) {
        System.out.println("send back: " + "Message to queue");
        template.convertAndSend("back-queue","Message to queue");
    }
}
