package edu.mum.weather.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteInvocationExecutor;
import org.springframework.remoting.support.RemoteInvocationFactory;
import org.springframework.security.remoting.rmi.ContextPropagatingRemoteInvocation;
import org.springframework.security.remoting.rmi.ContextPropagatingRemoteInvocationFactory;

import edu.mum.weather.AuthenticationService;
import edu.mum.weather.AuthenticationServiceImpl;
import edu.mum.weather.WeatherService;
import edu.mum.weather.WeatherServiceImpl;

@Configuration
@ImportResource("classpath:/context/applicationContext.xml")
public class WeatherConfigServer {

	   @Bean
	    public WeatherService weatherService() {
	        WeatherService wService = new WeatherServiceImpl();
	        return wService;
	    }

	   @Bean
	    public AuthenticationService authenticationService() {
		   AuthenticationService authenticationService = new AuthenticationServiceImpl();
	        return authenticationService;
	    }

    @Bean
    public RmiServiceExporter rmiAuthenticationService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("AuthenticationService");
        rmiService.setServiceInterface(AuthenticationService.class);
        rmiService.setService(authenticationService());
                  return rmiService;
    }

    @Bean
    public RmiServiceExporter rmiWeatherService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("WeatherService");
        rmiService.setServiceInterface(WeatherService.class);
        rmiService.setService(weatherService());
        return rmiService;
    }
 

     
}
