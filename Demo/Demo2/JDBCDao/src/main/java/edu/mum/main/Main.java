package edu.mum.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.main.JavaConfiguration;
import edu.mum.dao.VehicleDao;
import edu.mum.domain.Vehicle;

 
@Component
public class Main {

	@Autowired
	VehicleDao vehicleDao;
	  

    public static void main(String[] args) throws Exception {
 
      	 ApplicationContext applicationContext = new AnnotationConfigApplicationContext( JavaConfiguration.class );

 //       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.getBean(Main.class).mainInternal(applicationContext);
      }
    
        private void mainInternal(ApplicationContext applicationContext) {
    	
    	
    	
 /*
     	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");
*/  
        
        
        Vehicle vehicle = new Vehicle("TEM0001", "Red", 4, 4);
        vehicleDao.insert(vehicle);

        vehicle = vehicleDao.findByVehicleNo("TEM0001");
        System.out.println("Vehicle No: " + vehicle.getVehicleNo());
        System.out.println("Color: " + vehicle.getColor());
        System.out.println("Wheel: " + vehicle.getWheel());
        System.out.println("Seat: " + vehicle.getSeat());
    }

}
