package com.rrl.wms.integration;

import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tibco.tibjms.TibjmsConnectionFactory;

@Component
public class TibcoAsyncConsumer implements MessageListener{
	
	@Value("${jms.ServerURL}")
	private String brokerUrl;
	@Value("${jms.UserName}")
	private String userName;
	@Value("${jms.Password}")
	private String userPassword;
	@Value("${jms.QueueName}")
	private String inputQueueName;

	@PostConstruct
	public void init() {
		try {
			TibjmsConnectionFactory jmsConnectionFctory = tibcoJmsConnectionFactory();
			Connection jmsConnection = jmsConnectionFctory.createConnection();
			Session jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue jmsQueue = jmsSession.createQueue(inputQueueName);
			MessageConsumer messageConsumer = jmsSession.createConsumer(jmsQueue);
			messageConsumer.setMessageListener(this);
			jmsConnection.start();
		} catch (JMSException jmex) {
			throw new RuntimeException("Exception in initalization of TibcoAsyncConsumer/Creating TIBCO Connection",
					jmex);
		}

	}
	
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("textMessage::::" + textMessage.getText());
			} catch (Exception ex) {
			   new RuntimeException("Failed to read Message", ex);
			}

		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
		
	}
	
	@Bean
	public TibjmsConnectionFactory tibcoJmsConnectionFactory()
	{
		TibjmsConnectionFactory tibcoJmsConnectionFactory = new TibjmsConnectionFactory(brokerUrl);
		tibcoJmsConnectionFactory.setUserName(userName);
		tibcoJmsConnectionFactory.setUserPassword(userPassword);
		return tibcoJmsConnectionFactory;
	}

}
