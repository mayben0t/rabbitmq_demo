package com.example.mq.testexc;

import com.google.common.collect.Maps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer_2 {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = CoolUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(CoolUtils.QUEUE_NAME_B,false,false,false,null);
        Map<String,Object> map = Maps.newHashMap();
        map.put("from","dream");
        channel.queueBind(CoolUtils.QUEUE_NAME_B,CoolUtils.EXCHANGE_NAME,"",map);

        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                try {
                    System.out.println("consumer_2 received: " + new String(body, "UTF-8"));
                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };



        channel.basicConsume(CoolUtils.QUEUE_NAME_B,false,consumer);
    }
}
