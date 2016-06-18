package edu.mum.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.domain.BookOrder;


public class WarehouseMessageListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseMessageListener.class);

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        BookOrder bookBookOrder = null;
		try {
			bookBookOrder = (BookOrder) objectMessage.getObject();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("WAREHOUSE - Message received: " );
        System.out.println("         product Name: "  + bookBookOrder.getTitle());

    }
}
