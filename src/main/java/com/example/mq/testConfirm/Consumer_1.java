package com.example.mq.testConfirm;

import com.example.mq.testexc.CoolUtils;
import com.google.common.collect.Maps;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Consumer_1 {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = CoolUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("AAA",false,false,false,null);
        channel.exchangeDeclare(CoolUtils.EXCHANGE_NAME, ExchangeTypes.DIRECT);
        Map<String,Object> map = Maps.newHashMap();
        map.put("to","faiz");
        channel.queueBind("AAA",CoolUtils.EXCHANGE_NAME,CoolUtils.ROUTING_KEY_A,null);
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



        channel.basicConsume("AAA",false,consumer);
    }
}
