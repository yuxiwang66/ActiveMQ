package com.yuxi.p2p;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener{

    @Override
    public void onMessage(Message message) {

        try {
            TextMessage textMessage=(TextMessage)message;
            System.out.println("从MQ获取消息"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
