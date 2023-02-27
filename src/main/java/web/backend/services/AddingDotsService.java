package web.backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.backend.repositories.DotsRepository;
import web.backend.util.DTO.AddDotDTO;
import web.backend.util.DTO.SendDotsDTO;
import web.backend.util.DotEntity;
import web.backend.util.area.AreaChecker;

@Service
public class AddingDotsService {
    private final RabbitTemplate template;
    private final DotsRepository repository;
    private final ObjectMapper mapper;

    public AddingDotsService(
            @Qualifier("backRabbitTemplate") RabbitTemplate template,
            DotsRepository dotsRepository,
            ObjectMapper mapper) {
        this.template = template;
        this.repository = dotsRepository;
        this.mapper = mapper;
    }

    public void handlingNewDot(AddDotDTO data) {
        DotEntity dot = new DotEntity(data.getX(), data.getY(), data.getR(), data.getDate(), data.getOwner());
        dot.setInArea(checkArea(dot));
        dot.setTime( System.currentTimeMillis() - dot.getDate());
        SendDotsDTO dto = new SendDotsDTO(dot);
        send(dto);
        repository.addDot(dot.getX(), dot.getY(), dot.getR(), dot.getDate(), dot.getTime(), dot.getOwner(), dot.getInArea());
    }

    private Integer checkArea(DotEntity dot) {
        AreaChecker areaChecker = new AreaChecker();
        return areaChecker.isInArea(dot.getX(), dot.getY(), dot.getR()) ? 1 : 0;
    }

    @RabbitListener(queues = "adding-dots-query")
    public void receive(String jsonMessage) {
        handlingNewDot(new AddDotDTO(jsonMessage));
    }

    private void send(SendDotsDTO data) {
        template.convertAndSend("back-queue", data.toJsonString());
    }
}
