package com.offcn.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class TestQueue1 {

    @Test
    public void testSend() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext-jms-consumer.xml");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
