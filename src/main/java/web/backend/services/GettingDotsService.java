package web.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.backend.repositories.DotsRepository;
import web.backend.util.DTO.GetDotsDTO;
import web.backend.util.DTO.SendDotsDTO;
import web.backend.util.DotEntity;

import java.util.LinkedList;
import java.util.List;

@Service
public class GettingDotsService {

    private final RabbitTemplate template;
    private final DotsRepository repository;

    public GettingDotsService(@Qualifier("backRabbitTemplate") RabbitTemplate template, DotsRepository repository) {
        this.template = template;
        this.repository = repository;
    }

    private void handlingMessage(GetDotsDTO data) {
        String owner = data.getOwner();
        LinkedList<DotEntity> dots = repository.getDotsByOwner(owner);
        SendDotsDTO dto = new SendDotsDTO(dots);
        send(dto);
    }

    @RabbitListener(queues = "getting-dots-query")
    private void receive(String data) {
        handlingMessage(new GetDotsDTO().fromJsonString(data));
    }

    private void send(SendDotsDTO data) {
        template.convertAndSend("back-queue", data.toJsonString());
    }
}
