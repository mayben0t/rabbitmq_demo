package com.example.mq.testexc;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = CoolUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(CoolUtils.EXCHANGE_NAME, ExchangeTypes.FANOUT);
//        channel.queueDeclare(CoolUtils.QUEUE_NAME_A,false,false,false,null);

        channel.basicQos(1);
        for(int i=0;i<50;i++){
            String msg = "demo test"+i;
            Thread.sleep(i*20);
            channel.basicPublish(CoolUtils.EXCHANGE_NAME,"",null,msg.getBytes());
            System.out.println("producer has sent:"+msg);
        }
        channel.close();
        connection.close();


//        channel.basicPublish(CoolUtils.EXCHANGE_NAME,);
    }
}
