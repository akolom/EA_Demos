package edu.mum.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("topicMessageSender")
public class SimpleTopicMessageSender implements MessageSender {
    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTemplate;
    
    

    public void sendMessage(final Object message) {
        this.jmsTemplate.send("mumEA.topic",new MessageCreator() {
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createObjectMessage((Serializable) message);
            }
          });
    }
}
