package com.example.mq.testexc;

import com.google.common.collect.Maps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer_1 {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = CoolUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(CoolUtils.QUEUE_NAME_A,false,false,false,null);
        channel.exchangeDeclare(CoolUtils.EXCHANGE_NAME, ExchangeTypes.DIRECT);
        Map<String,Object> map = Maps.newHashMap();
        map.put("to","faiz");
        channel.queueBind(CoolUtils.QUEUE_NAME_A,CoolUtils.EXCHANGE_NAME,"",map);
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                try {
                    System.out.println("consumer_1 received: " + new String(body, "UTF-8"));
                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };



        channel.basicConsume(CoolUtils.QUEUE_NAME_A,false,consumer);
    }
}
