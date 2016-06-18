package edu.mum;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.sender.MessageSender;
import edu.mum.weather.TemperatureInfo;
import edu.mum.weather.WeatherService;

public class JmsSenderMain {
    public static void main(String[] args) {
    	
 
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/spring/jms-sender-app-context.xml",
        		"classpath:META-INF/spring/jms-listener-app-context.xml");
        context.refresh();
        
     	WeatherService weatherService   =     (WeatherService) context.getBean("weatherService");

        MessageSender messageSender = context.getBean("messageSender", MessageSender.class);
        MessageSender topicMessageSender = context.getBean("topicMessageSender", MessageSender.class);


        List<Date> dates = Arrays.asList(new Date[]{new Date()});
        String[] city =  {"San Francisco, CA","Los Angoles, CA", "London","Manchester","Fairfield",
        		"Iowa City", "Des Monies","Dallas","Auston","Eritrea"};
       
        for(int i=0; i < 10; i++) {
            List<TemperatureInfo> tempInfo = weatherService.getTemperatures(city[i],  dates);
            
           topicMessageSender.sendMessage(tempInfo.get(0));
           messageSender.sendMessage(tempInfo.get(0));
        }
    }
}
