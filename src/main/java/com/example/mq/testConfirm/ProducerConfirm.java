package com.example.mq.testConfirm;

import com.example.mq.testexc.CoolUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerConfirm {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = CoolUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("AAA",false,false,false,null);
        channel.confirmSelect();
        for(int i=0;i<10;i++)
        channel.basicPublish("","AAA",null,"可能你也会想呢".getBytes());
        boolean b = channel.waitForConfirms();
        if(b){
            System.out.println("没问题");
        }else {
            System.out.println("有问题");
        }

    }
}
