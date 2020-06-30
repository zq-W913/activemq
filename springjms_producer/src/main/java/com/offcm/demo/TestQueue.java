package com.offcm.demo;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestQueue {

    @Test
    public void testSend() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext-jms-producer.xml");

        QueueProducer producer = context.getBean(QueueProducer.class);

        producer.sendTextMessage("SpringJms-点对点");
    }
}
