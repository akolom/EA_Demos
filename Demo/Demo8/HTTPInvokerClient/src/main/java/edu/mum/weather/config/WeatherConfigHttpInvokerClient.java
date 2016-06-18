package edu.mum.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import edu.mum.weather.WeatherService;
import edu.mum.weather.WeatherServiceClient;

@Configuration
public class WeatherConfigHttpInvokerClient {
    
    @Bean
    public HttpInvokerProxyFactoryBean weatherService() {
    HttpInvokerProxyFactoryBean factory = new HttpInvokerProxyFactoryBean();
	factory.setServiceUrl("http://localhost:8080/HTTPInvokerServer/weather");
	factory.setServiceInterface(WeatherService.class);
	return factory;
    }

    @Bean
    public WeatherServiceClient weatherClient() { 
	WeatherServiceClient wServiceClient = new WeatherServiceClient();
	return wServiceClient;
    }
    
}
