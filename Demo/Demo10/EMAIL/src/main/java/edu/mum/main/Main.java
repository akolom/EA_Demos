package edu.mum.main;


import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Item;
import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.OrderPayment;
import edu.mum.domain.Product;
import edu.mum.emailservice.EmailService;


		
public class Main {
  public static void main(String[] args) throws MessagingException {

    ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext.xml");

    /*Product product = new Product("Alarm Clock", "Simple & Automatic", 79);*/
    
    Item item = new Item(12345, "Dell Laptop", "Core I7", 770);
    OrderPayment orderPayment = new OrderPayment();
    /*OrderItem orderItem = new OrderItem(2, product);*/
    OrderItem orderItem = new OrderItem(2, item);
    Set<OrderItem> orderItems = new HashSet<OrderItem>();
    orderItems.add(orderItem);
    Order order = new Order("B123",orderItems,orderPayment);
    
    String documentName = "AlarmClock.docx";
    EmailService emailService = (EmailService) context.getBean("emailService");
    emailService.sendOrderReceivedMail("Akolom", "akolom.g2013@gmail.com",order,documentName,new Locale("en"));
  
    System.out.println("                      The Email is on the WAY!!!");

  }
  
  }