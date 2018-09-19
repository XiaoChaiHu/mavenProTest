package com.example.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ConsumerTopic {

    @Test
    public void test() throws JMSException {
        //创建连接
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.1.6:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage){
                TextMessage textMessage=(TextMessage) message;
                try {
                    System.out.println("ConsumerTopic::test::TextMessage::"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        while (true){

        }
    }
}
