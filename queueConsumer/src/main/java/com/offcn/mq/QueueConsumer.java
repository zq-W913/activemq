package com.offcn.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

//点对点模式的消费者QueueConsumer
public class QueueConsumer {
    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.188.129:61616");
        //创建session链接
        Connection connection = activeMQConnectionFactory.createConnection();
        //开启链接
        connection.start();
        //获取session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue wzq = session.createQueue("wzq");
        //创建消息消费者
        MessageConsumer consumer = session.createConsumer(wzq);
        //进行监听
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage ){
               TextMessage   textMessage=   (TextMessage)message;
                    try {
                        String text = textMessage.getText();
                        System.out.println("收到消息了："+text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //等待键盘输入
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
