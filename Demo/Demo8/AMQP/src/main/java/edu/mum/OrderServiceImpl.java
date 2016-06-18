package edu.mum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.Product;

public class OrderServiceImpl implements OrderService {
    public void publish(RabbitTemplate rabbitTemplate) {
        Product product = new Product("Bicycle", "Two Wheeler", 79);
        OrderPayment orderPayment = new OrderPayment();
        OrderItem orderItem = new OrderItem(2, product);
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        orderItems.add(orderItem);
        Order order = new Order("B123",orderItems,orderPayment);

        rabbitTemplate.convertAndSend("purchases.y",order);


        	order.getItems().get(0).getProduct().setName("Chandelier");
            rabbitTemplate.convertAndSend("purchases.y",order);
 
    	order.getItems().get(0).getProduct().setName("Out of Stock");
        rabbitTemplate.convertAndSend("purchases.y",order);
        
        
        // Second subscriber queue
       	order.getItems().get(0).getProduct().setName("Leather Shoes");
        rabbitTemplate.convertAndSend("purchases.x",order);
 
       	order.getItems().get(0).getProduct().setName("Donut Holes");
        rabbitTemplate.convertAndSend("purchases.x",order);
 
    }
}
