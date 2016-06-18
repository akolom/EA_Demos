package edu.mum;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AmqpMain {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/spring/order-app-context.xml");
        context.refresh();

      }
}
