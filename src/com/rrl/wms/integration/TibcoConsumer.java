package com.rrl.wms.integration;

import com.tibco.tibjms.TibjmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;

@Component
public class TibcoConsumer {

    @Value("${jms.QueueName}")
    String destinationQueue;

    @Value("${jms.ServerURL}")
    String serverURL;

    @Value("${jms.UserName}")
    String userName;

    @Value("${jms.Password}")
    String password;

    public String receive(){
        return (String)jmsTemplate().receiveAndConvert(destinationQueue);
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