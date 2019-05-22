package com.example.mq.controller;


import com.example.mq.service.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    MsgProducer msgProducer;

    @GetMapping("/hi")
    public String hello(){
        msgProducer.sendMsg("cross the great wall,we can arrive anywhere on the world");
        return "hi";
    }


}
