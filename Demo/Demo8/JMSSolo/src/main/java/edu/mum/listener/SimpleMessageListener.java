package edu.mum.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.weather.TemperatureInfo;

public class SimpleMessageListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageListener.class);

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        TemperatureInfo tempInfo = null;
		try {
			tempInfo = (TemperatureInfo) objectMessage.getObject();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*logger.info("Message received: " );
		logger.info("         City: "  + tempInfo.getCity());
		logger.info("               Min: "  + tempInfo.getMin());
		logger.info("               Max: "  + tempInfo.getMax());
		logger.info("               Average: "  + tempInfo.getAverage());
		logger.info("                Date: " + tempInfo.getDate());
		*/
		System.out.println("Testing ........");
    }
}
