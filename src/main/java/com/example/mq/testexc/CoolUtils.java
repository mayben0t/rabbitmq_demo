package com.example.mq.testexc;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CoolUtils {
    public static final String EXCHANGE_NAME = "cool_exchange";
    public static final String QUEUE_NAME_A = "cool_queue_A";
    public static final String QUEUE_NAME_B = "cool_queue_B";


    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        return factory.newConnection();
    }
}
