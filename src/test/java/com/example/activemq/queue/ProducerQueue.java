package com.example.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ProducerQueue {

    /**
     * 默认的消息是持久化的
     *
     * @throws JMSException
     */
    @Test
    public void test() throws JMSException {
        //创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.1.6:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(queue);
        //producer.setDeliveryMode(DeliveryMode.PERSISTENT); 不写默认是支持持久化的
        //使用会话对象创建一个消息对象
        TextMessage textMessage = session.createTextMessage("我有毒");
        //发送消息
        producer.send(textMessage);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 持久化机制
     * 设置消息不持久化
     *
     * @throws JMSException
     */
    @Test
    public void testNonPersistent() throws JMSException {
        //创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.1.6:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(queue);
        //设置不持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //使用会话对象创建一个消息对象
        TextMessage textMessage = session.createTextMessage();
        for (int i = 0; i < 10; i++) {
            textMessage.setText("我是生产者发送的第" + i + "条消息");
            //发送消息
            producer.send(textMessage);
        }
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 确认机制
     * 客户端手动确认
     *
     * @throws JMSException
     */
    @Test
    public void testClientAcknowledge() throws JMSException {
        //创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.1.6:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建会话（session）对象
        //这里使用客户端手动确认
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(queue);
        //设置不持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //使用会话对象创建一个消息对象
        TextMessage textMessage = session.createTextMessage();
        for (int i = 0; i < 10; i++) {
            String text = "我是生产者发送的第" + i + "条消息";
            textMessage.setText(text);
            //发送消息
            producer.send(textMessage);
            System.out.println("testClientAcknowledge::textMessage::" + text);
            //客户端手动确认，实验证明无影响
            //textMessage.acknowledge();
        }
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * 事物
     * @throws JMSException
     */
    @Test
    public void testTransacted() throws JMSException {
        //创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.1.6:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用连接对象创建会话（session）对象
        //这里使用客户端手动确认
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        //使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(queue);
        //设置不持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //使用会话对象创建一个消息对象
        TextMessage textMessage = session.createTextMessage();
        for (int i = 0; i < 10; i++) {
            String text = "我是生产者发送的第" + i + "条消息";
            textMessage.setText(text);
            //发送消息
            producer.send(textMessage);
            System.out.println("testClientAcknowledge::textMessage::" + text);
            if (textMessage.getText().indexOf("第6条消息")!=-1){
                System.out.println("第六条消息回滚");
                session.rollback();
            }
        }
        session.commit();
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
