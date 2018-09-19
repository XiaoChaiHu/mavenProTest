package com.example.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ConsumerQueue {

    @Test
    public void test() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.1.6:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //该方式为自动确认
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage){
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("Consumer::test::textMessage::"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        while (true){

        }
    }

    /**
     * 确认机制
     * 客户端手动确认
     * @throws JMSException
     */
    @Test
    public void testClientAcknowledge() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.1.6:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //改方式为客户端手动确认
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage){
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("Consumer::test::textMessage::"+textMessage.getText());
                    //手动确认，不确认的话消费者消费该条消息后这条消息还会在队列中
                    //textMessage.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        while (true){

        }
    }
}
