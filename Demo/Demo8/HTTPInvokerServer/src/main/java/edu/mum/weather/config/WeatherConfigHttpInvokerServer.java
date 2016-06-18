package edu.mum.weather.config;

import edu.mum.weather.WeatherService;
import edu.mum.weather.WeatherServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
 
@Configuration
public class WeatherConfigHttpInvokerServer {

    @Bean
    public WeatherService weatherService() {
        WeatherService wService = new WeatherServiceImpl();
        return wService;
    }

    @Bean(name = "/weather")
    public HttpInvokerServiceExporter exporter() {
    	HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(weatherService());
        exporter.setServiceInterface(WeatherService.class);
        return exporter;
    }
}
