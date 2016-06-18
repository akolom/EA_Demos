package edu.mum.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("messageSender")
public class SimpleMessageSender implements MessageSender {
    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;
    

   /* GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:META-INF/spring/jms-sender-app-context.xml",
    		"classpath:META-INF/spring/jms-listener-app-context.xml");*/

    public void sendMessage(final Object message) {
        this.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createObjectMessage((Serializable) message);
            }
          });
    }
}
