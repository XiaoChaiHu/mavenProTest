package com.example.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ProducerTopic {

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
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("发布订阅测试");
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }
}
