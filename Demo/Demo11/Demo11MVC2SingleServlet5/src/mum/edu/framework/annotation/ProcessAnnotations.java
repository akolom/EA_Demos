package mum.edu.framework.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import mum.edu.framework.controller.Controller;
import mum.edu.framework.factory.InjectionFactory;

public class ProcessAnnotations {

	/*
	 * Loop through Controllers looking for Annotations [@Autowired,@RequestMapping]
	 */
	public static void handleAnnotations(Map<String,Controller> controllers, Map<String,Controller> dispatchers, Map<String, String> dispatcherMethods) {
		
		Set<String> keys = (Set<String>) controllers.keySet();
		
		for ( String key : keys) {
			
			Controller controller = controllers.get(key);
			
			//Request Mapping annotation
			Method[] methods = controller.getClass().getMethods();
			
			for (Method method : methods) {
				if(method.isAnnotationPresent(RequestMapping.class)){
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					 
						String[] uriList = requestMapping.value();
 						
						String methodName = method.getName();
						
 						for (String uri : uriList) {
		    		    	dispatchers.put(uri, controller);
						dispatcherMethods.put(uri, methodName);
						}
						
				}
			}
			
			// If Autowired, Create instance of Object and set it in Controller
			Field[] fields = controller.getClass().getDeclaredFields();	    
			for (Field field : fields) {
 					AutoWired dependency =   field.getAnnotation(AutoWired.class);
					if (dependency != null) {
						try {
							Class<?> fieldClass =	field.getType();
							Object setter =  InjectionFactory.getInstance(fieldClass.getName());
 							field.setAccessible(true);
							field.set(controller,setter);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
 			}

		}
	}
}
