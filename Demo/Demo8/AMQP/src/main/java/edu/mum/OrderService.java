package edu.mum;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import edu.mum.domain.Order;

public interface OrderService {
    public void publish(RabbitTemplate rabbitTemplate);
}
