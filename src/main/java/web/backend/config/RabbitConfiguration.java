package web.backend.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.log4j.Logger;

@Configuration
@EnableRabbit
public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate backRabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public RabbitTemplate routeRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange("exchange");
        return rabbitTemplate;
    }

    @Bean
    public Queue addingQuery() {
        return new Queue("adding-query");
    }

    @Bean
    public Queue sharingQuery() {
        return new Queue("sharing-query");
    }

    @Bean
    public Queue getDotsQuery() {
        return new Queue("getting-dots-query");
    }

    @Bean
    public Queue backQuery() {
        return new Queue("back-query");
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("back-queue");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange");
    }

    @Bean
    public Binding errorBinding1() {
        return BindingBuilder.bind(addingQuery()).to(directExchange()).with("add-with-share");
    }

    @Bean
    public Binding errorBinding2() {
        return BindingBuilder.bind(sharingQuery()).to(directExchange()).with("add-with-share");
    }

    @Bean
    public Binding shareBinding() {
        return BindingBuilder.bind(sharingQuery()).to(directExchange()).with("share-dots");
    }

    @Bean
    public Binding addBinding() {
        return BindingBuilder.bind(addingQuery()).to(directExchange()).with("add-dots");
    }

    @Bean
    public Binding getDotsBinding() {
        return BindingBuilder.bind(getDotsQuery()).to(directExchange()).with("get-dots");
    }
}
