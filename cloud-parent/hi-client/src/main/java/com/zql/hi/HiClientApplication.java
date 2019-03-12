package com.zql.hi;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient     // 表明自己是一个服务（client）
@RestController
public class HiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    @ApiOperation("根据传入的姓名向用户问好")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") @ApiParam(name = "name",value = "用户姓名") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

}
