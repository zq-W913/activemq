package com.offcn.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;


/*消息生产者*/
public class topicConsumer {
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
         //创建消费者
         MessageConsumer consumer = session.createConsumer(topic);
         //创建进行监听
         consumer.setMessageListener(new MessageListener() {
             public void onMessage(Message message) {

                 if (message instanceof MapMessage ) {
                     MapMessage mapMessage= (MapMessage)message;
                     try {
                         String q =  mapMessage.getString("权哥");
                         int age = mapMessage.getInt("age");
                         System.out.println(q+"-----"+age);
                     } catch (JMSException e) {
                         e.printStackTrace();
                     }
                 }
             }
         });
         //等待键盘
         try {
             System.in.read();
         } catch (IOException e) {
             e.printStackTrace();
         }
         //关闭资源
         consumer.close();
         session.close();
         connection.close();
     }
}
