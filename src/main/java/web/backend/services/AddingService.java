package web.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import web.backend.repositories.DotsRepository;
import web.backend.util.DTO.DotDTO;
import web.backend.util.DotEntity;
import web.backend.util.area.AreaChecker;

@Service
public class AddingService {
    private final RabbitTemplate template;
    private final DotsRepository repository;
    private final ObjectMapper mapper;

    public AddingService(
            @Qualifier("backRabbitTemplate") RabbitTemplate template,
            DotsRepository dotsRepository,
            ObjectMapper mapper) {
        this.template = template;
        this.repository = dotsRepository;
        this.mapper = mapper;
    }

    public void handlingNewDot(String data) throws JsonProcessingException {
        DotEntity dot = mapper.readValue(data, DotEntity.class);
        System.out.println("accepted on AddingService: " + dot.toString());
        dot.setInArea(checkArea(dot));
        dot.setTime((int) System.currentTimeMillis() - dot.getDate());
//        DotDTO dto = new DotDTO(dot, "dot");
//        send(dto);
        System.out.println(mapper.writeValueAsString(dot));
        repository.addDot(dot.getX(), dot.getY(), dot.getR(), dot.getDate(), dot.getTime(), dot.getOwner(), dot.getCreator(), dot.getInArea());
        System.out.println(repository.getDotsByOwner("2"));
    }

    private Boolean checkArea(DotEntity dot) {
        AreaChecker areaChecker = new AreaChecker();
        return areaChecker.isInArea(dot.getX(), dot.getY(), dot.getR());
    }

    @RabbitListener(queues = "adding-query")
    public void receive(String data) {
        try {
            handlingNewDot(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(DotDTO dto) {
        System.out.println("send back: " + dto.toString());
        template.convertAndSend("back-queue", dto);
    }
}
