package web.backend.components;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    //TODO: Придумать как использовать

    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "adding-query")
    public void worker1(String message) {
        logger.info("accepted on worker 1 : " + message);
    }

    @RabbitListener(queues = "sharing-query")
    public void worker2(String message) {
        logger.info("accepted on worker 2 : " + message);
    }

}
