package com.offcn.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
/*进行整合之后必须监听接口 自定义监听类*/
@Component/*要不再xml配置文件new对象相当于《bean'class ，要不就加注解注入到springxml配置我配置文件当中*/
public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
         TextMessage textMessage   =(TextMessage) message;
            try {
                String text = textMessage.getText();
                System.out.println("接收到的信息是：" + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}