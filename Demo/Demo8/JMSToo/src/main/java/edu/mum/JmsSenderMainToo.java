package edu.mum;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;

import edu.mum.sender.MessageSender;
import edu.mum.weather.TemperatureInfo;
import edu.mum.weather.WeatherService;
import java.util.List;

public class JmsSenderMainToo {
    public static void main(String[] args) {
    	
 
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/spring/jms-sender-app-context.xml");
        context.refresh();
        
        MessageSender messageSender = context.getBean("messageSender", MessageSender.class);
     	WeatherService weatherService =  (WeatherService) context.getBean("weatherService");
     
        // Get data to send...
       List<Date> dates = Arrays.asList(new Date[]{new Date()});
        String[] city =  {"New York","New Jersey", "New Rochelle", "New England","New Town","New Flower",
        		"Newt", "New Delhi","New Boston","New Lancaster","New Amsterdam"};   
        for(int i=0; i < 10; i++) {
            List<TemperatureInfo> tempInfo = weatherService.getTemperatures(city[i],  dates);
            
            // Send it...
            messageSender.sendMessage(tempInfo.get(0));
        }
    }
}
