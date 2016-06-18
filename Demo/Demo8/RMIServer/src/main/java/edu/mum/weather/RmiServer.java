package edu.mum.weather;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiServer {

    public static void main(String[] args) {
 //   	ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext.xml");
        new AnnotationConfigApplicationContext("edu.mum.weather.config");
    }
}
