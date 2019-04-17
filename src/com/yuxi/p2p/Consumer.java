package com.yuxi.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址

    public static void main(String[] args) {


        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接
        Session session;//会话
        Destination destination;//消息目的地
        MessageConsumer messageConsumer;//消息消费者



        try {
            connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
            connection = connectionFactory.createConnection();//通过工厂获取连接
            connection.start();//启动连接
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);//创建session
            destination = session.createQueue("短信发送");
            messageConsumer =  session.createConsumer(destination);//创建消息消费者

/*            //从MQ服务器获取消息
            for(int i=0;i<5;i++){
                TextMessage textMessage= (TextMessage) messageConsumer.receive();
                System.out.println(textMessage.getText());
            }*/

            //写MQ的监听器，监听时，不能 关闭连接，这个监听要一直执行
            messageConsumer.setMessageListener(new MyMessageListener());
            session.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
/*            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }*/
        }
    }





}
