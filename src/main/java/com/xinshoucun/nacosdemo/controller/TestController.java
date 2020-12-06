package com.xinshoucun.nacosdemo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @NacosValue(value = "${nacos.test}", autoRefreshed = true)
    private String value;

    @GetMapping("/index")
    public String index(){
        System.out.println("当前时间:"+System.currentTimeMillis() + "-获取到的Nacos值:"+value);
        return "Hello World";
    }

}
