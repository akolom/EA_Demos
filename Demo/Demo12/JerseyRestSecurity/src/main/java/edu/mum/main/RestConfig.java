package edu.mum.main;

 import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import edu.mum.rest.service.ProductRestService;

 	public class RestConfig extends ResourceConfig {
		
		public RestConfig() {
 
			System.out.println("YYYYESS");
 
 		    register(RequestContextFilter.class);
 		    register(ProductRestService.class);
		}
	}

