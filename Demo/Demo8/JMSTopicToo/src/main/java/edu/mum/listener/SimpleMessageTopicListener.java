package edu.mum.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.mum.weather.TemperatureInfo;

public class SimpleMessageTopicListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageTopicListener.class);

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        TemperatureInfo tempInfo = null;
		try {
			tempInfo = (TemperatureInfo) objectMessage.getObject();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logger.info("*******  EXTRA APP -- TOPIC Message received: ************" );
		logger.info("         City: "  + tempInfo.getCity());
		logger.info("               Min: "  + tempInfo.getMin());
		logger.info("               Max: "  + tempInfo.getMax());
		logger.info("               Average: "  + tempInfo.getAverage());
    }
}
