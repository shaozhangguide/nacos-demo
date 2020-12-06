package com.xinshoucun.nacosdemo.test;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args){

//        scheduleWithFixedDelayTaskTest();
  //      scheduleTaskAtFixedRateTest();
        getConfigs();
//        new NacosThreadTest();
        while (true) {
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void scheduleTaskAtFixedRateTest(){
        ScheduledExecutorService respScheduler = new ScheduledThreadPoolExecutor(2);
        System.out.println("task begin:"+new Date());
        /**
         *  对于scheduledAtFixedRate 执行定时任务。第二次以及后续每一次任务的执行，取决于两个条件：
         *  第一：如果任务的执行时间，大于period ，后续任务的调用频率为该任务的方法执行时间。这个period，在后续执行任务时，
         *  始终为固定的。不会因为，后续执行时间较长，再进行更改。
         *  第二：如果任务的执行时间，小于period，后续任务的调用频率为period。
         */
        respScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    long c = 1605082917000L;
                    if(System.currentTimeMillis() <= c){
                        Thread.sleep(1000L);//2000
                    }else {
                        Thread.sleep(4000L);//2000
//                        int randomInt = new Random().nextInt(10);
//                        System.out.println("随机字符串"+randomInt);
//                        if(randomInt % 4 == 0){
//                            Thread.sleep(5000);
//                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--task run:"+new Date());
            }
        },2,3, TimeUnit.SECONDS);
    }

    public static void scheduleWithFixedDelayTaskTest(){
        ScheduledExecutorService respScheduler = new ScheduledThreadPoolExecutor(2);
        System.out.println("task begin:"+new Date());
        int i = 1;
        /**
         *: 对于scheduleWithFixedDelay 执行定时任务，
         * 第二次以及后续每一次任务的执行，时间为：delay + 上次执行任务所需的时间。
         */
        respScheduler.scheduleWithFixedDelay(() -> {
            try {
                long c = 1604996517000L;
                if(System.currentTimeMillis() <= c){
                    Thread.sleep(1000L);//2000
                }else {
                    Thread.sleep(4000L);//2000
//                    int randomInt = new Random().nextInt(10);
//                    System.out.print("随机字符串"+randomInt+"---");
//                    if(randomInt % 2 == 0){
////                        System.out.println("---进行取模运算:");
//                        Thread.sleep(5000L);
////                        System.out.println("---取模Sleep结束:"+new Date());
//                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"WithFixedDelayTaskTest:"+new Date());
        },1L,3L, TimeUnit.SECONDS);
    }

    public static void getConfigs(){
        try {
            String serverAddr = "39.100.39.41:8848";
            String dataId = "application.properties";
            String group = "DEFAULT_GROUP";
            //1.配置nacos 属性
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            properties.put("namespace", "73c2c97a-d8d1-44bb-a98a-ee5e5fd3cbb6");
            //2.创建ConfigService对象
            ConfigService configService = NacosFactory.createConfigService(properties);
            //3.获取配置文件（只不过这里的只会获取一次，不适合当配置文件修改时，获取内容。）
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println("获取到的content:"+content);
            //4.当在nacos 管理平台修改配置文件内容时，可以及时获取最新配置文件
            configService.addListener(dataId, group, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("配置已经更改，获取到的Content:"+configInfo);
                }
            });
            System.out.println();
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
