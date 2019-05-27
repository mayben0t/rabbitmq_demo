package com.example.mq.filter;

import com.example.mq.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Order(1)
@WebFilter(urlPatterns = "/*",filterName = "logFilter")
public class LogFilter implements Filter {
//    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(filterConfig.getServletContext());
        RabbitTemplate rabbitTemplate = (RabbitTemplate)context.getBean(RabbitTemplate.class);
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, servletRequest.getRemoteAddr(), correlationId);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
