package com.rrl.wms.integration;

import javax.jms.*;

import com.tibco.tibjms.TibjmsConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component(value = "queueMsgSender")
public class TibcoSender {



    /*
    private JmsTemplate jmsTemplate;
    private static Logger logger =  org.apache.log4j.Logger.getLogger(TibcoSender.class);

    public void sendMessage(final String message, final String CorrId) {
        logger.info("preparing to send queue jms message");

        jmsTemplate.send(new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage(message);
                msg.setJMSCorrelationID(CorrId);
                return msg;
            }
        });
    }
    */

    @Value("${jms.QueueName}")
    String destinationQueue;

    @Value("${jms.ServerURL}")
    String serverURL;

    @Value("${jms.UserName}")
    String userName;

    @Value("${jms.Password}")
    String password;

    public void send(String msg){
        jmsTemplate().convertAndSend(destinationQueue, msg);
    }

    @Bean
    public ConnectionFactory connectionFactory(){

        TibjmsConnectionFactory connectionFactory = new TibjmsConnectionFactory(serverURL);
        connectionFactory.setUserName(userName);
        connectionFactory.setUserPassword(password);

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }


}