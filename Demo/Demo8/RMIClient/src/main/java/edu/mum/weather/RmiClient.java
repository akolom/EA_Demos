package edu.mum.weather;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class RmiClient {

    public static void main(String[] args) {
    ApplicationContext context =
            new AnnotationConfigApplicationContext("edu.mum.weather.config");
        WeatherServiceClient client = context.getBean(WeatherServiceClient.class);

        AuthenticationServiceClient authClient = context.getBean(AuthenticationServiceClient.class);
 
		Authentication clientAuthentication = new UsernamePasswordAuthenticationToken("Bill", "Bill");

		clientAuthentication = authClient.authenticate(clientAuthentication);

		if (clientAuthentication == null)
			System.out.println(" Authentication failed ");
		else 
			System.out.println(" Authentication successful ");
			

         TemperatureInfo temperature = client.getTodayTemperature("Houston");
        System.out.println("Min temperature : " + temperature.getMin());
        System.out.println("Max temperature : " + temperature.getMax());
        System.out.println("Average temperature : " + temperature.getAverage());
        

    }
}
