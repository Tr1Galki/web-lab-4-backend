package web.backend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.backend.repositories.DotsRepository;
import web.backend.util.DotDTO;
import web.backend.util.DotEntity;
import web.backend.util.area.AreaChecker;

@Service
public class AddingService {
    //TODO: реализовать

    private final RabbitTemplate template;
    private final DotsRepository repository;

    public AddingService(
            @Qualifier("backRabbitTemplate") RabbitTemplate template,
            DotsRepository dotsRepository) {
        this.template = template;
        this.repository = dotsRepository;
    }

    @Transactional
    public void handlingNewDot(DotEntity dot) {
        dot.setInArea(checkArea(dot));
        dot.setTime((int) System.currentTimeMillis() - dot.getDate());
        DotDTO dto = new DotDTO(dot, "dot");
        send(dto);

    }

    private Boolean checkArea(DotEntity dot) {
        AreaChecker areaChecker = new AreaChecker();
        return areaChecker.isInArea(dot.getX(), dot.getY(), dot.getR());
    }

    @RabbitListener(queues = "adding-query")
    private void receive(DotEntity dot) {
        System.out.println("accepted on AddingService: " + dot.toString());
        handlingNewDot(dot);
    }

    private void send(DotDTO dto) {
        System.out.println("send back: " + dto.toString());
        template.convertAndSend("back-queue", dto);
    }


}
