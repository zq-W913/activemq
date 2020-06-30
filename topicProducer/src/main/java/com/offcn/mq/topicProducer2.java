package com.offcn.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/*消息生产者*/
public class topicProducer2 {
     public static void main(String[] args) throws JMSException {
         //1.创建工厂
         ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.188.129:61616");
         //2.获取连接
         Connection connection = connectionFactory.createConnection();
         //启动连接
         connection.start();
         //获取session                                  是否开启事务   开启事务的方式 (参数1：是否启动事务,参数2：消息确认模式)
         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
         //创建话题对象主题对象,这就是个队列名字进行标识
         Topic topic = session.createTopic("zq");
         //创建生产者
         MessageProducer producer = session.createProducer(topic);
         //生产者创建消息
         MapMessage mapMessage = session.createMapMessage();
         mapMessage.setString("权哥","wudi");
         mapMessage.setInt("age",23);
         //发送消息
         producer.send(mapMessage);
         //9.关闭资源
         producer.close();
         session.close();
         connection.close();

     }
}
