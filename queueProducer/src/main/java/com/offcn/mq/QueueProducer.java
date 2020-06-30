package com.offcn.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/*消息生产者*/
public class QueueProducer  {
     public static void main(String[] args) throws JMSException {
         //1.创建工厂
       ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.188.129:61616");
        //2.获取连接
         Connection connection = connectionFactory.createConnection();
        //启动连接
         connection.start();
         //获取session                                  是否开启事务   开启事务的方式 (参数1：是否启动事务,参数2：消息确认模式)
         Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
          //创建队列对象
         Queue wzq = session.createQueue("wzq");//给对象起名
          //创建消息消息的生产者
         MessageProducer producer = session.createProducer(wzq);//把队列对象加入到生产者，当前生产者属于这个消息队列
         //创建消息
         TextMessage textMessage = session.createTextMessage("权爸爸你好，给你发消息了");
         //发送消息
                  producer.send(textMessage);
                  //关闭资源
         producer.close();
         session.close();
         connection.close();

     }
}
