package com.zql.lawliet.hiclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@Slf4j
public class LawlietHiclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawlietHiclientApplication.class, args);
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/test/hi")
    public String hi() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("lawliet-hi");
        String url = serviceInstance.getUri() + "/hi?name=zhang";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return "执行：" + url + "，返回：" + result;
    }
}
