package edu.mum.weather.config;

import edu.mum.weather.AuthenticationService;
import edu.mum.weather.AuthenticationServiceClient;
import edu.mum.weather.WeatherService;
import edu.mum.weather.WeatherServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.support.RemoteInvocationFactory;


@Configuration
public class WeatherConfigClient {

    @Bean
    public RmiProxyFactoryBean weatherService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost:1099/WeatherService");
        rmiProxy.setServiceInterface(WeatherService.class);
         return rmiProxy;
    }

    @Bean
    public RmiProxyFactoryBean authenticationService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost:1099/AuthenticationService");
        rmiProxy.setServiceInterface(AuthenticationService.class);
         return rmiProxy;
    }

    @Bean
    public WeatherServiceClient weatherClient() {
        WeatherServiceClient wServiceClient = new WeatherServiceClient();
        return wServiceClient;
    }

    @Bean
    public AuthenticationServiceClient authenticationClient() {
    	AuthenticationServiceClient authenticationServiceClient = new AuthenticationServiceClient();
        return authenticationServiceClient;
    }

 }



