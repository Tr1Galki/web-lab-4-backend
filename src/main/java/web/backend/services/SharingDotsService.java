package web.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.backend.repositories.DotsRepository;
import web.backend.repositories.UsersRepository;
import web.backend.util.DotEntity;

import java.util.LinkedList;

@Service
public class SharingDotsService {
    //TODO: реализовать

    private final DotsRepository dotsRepository;
    private final UsersRepository usersRepository;
    private final RabbitTemplate template;

    public SharingDotsService(
            DotsRepository dotsRepository,
            UsersRepository usersRepository,
            @Qualifier("backRabbitTemplate") RabbitTemplate template) {
        this.dotsRepository = dotsRepository;
        this.usersRepository = usersRepository;
        this.template = template;
    }

    private void handlingData(String data) {
        //TODO: обработка входа
        LinkedList<DotEntity> dots = new LinkedList<>();
        for (int i = 0; i < dots.size(); i++) {
            //реализовать добавление
        }
    }

    @RabbitListener(queues = "sharing-dots-query")
    private void receive(String message) {
        System.out.println("accepted on worker 2: " + message);
        send(message);
    }

    private void send(String message) {
        System.out.println("send back: " + "Message to queue");
        template.convertAndSend("back-queue","Message to queue");
    }
}
