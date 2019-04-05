package com.zql.lawliet.hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient  // 开启服务注册发现
@RestController
public class LawlietHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawlietHiApplication.class, args);
    }

    @GetMapping("/hi")
    public String hi(String name) {
        return "hello," + name;
    }
}
