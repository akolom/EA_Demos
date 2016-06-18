package edu.mum;

import org.springframework.context.support.GenericXmlApplicationContext;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class AmqpMain {
	
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/spring/order-app-context.xml");
        context.refresh();

        RabbitTemplate rabbitTemplate =  context.getBean(RabbitTemplate.class);
    	OrderServiceImpl orderService = new OrderServiceImpl();
    	orderService.publish(rabbitTemplate);

    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    //   context.close();
    }
}
