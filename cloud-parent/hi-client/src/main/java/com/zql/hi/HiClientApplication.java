package com.zql.hi;

import com.zql.hi.entity.User;
import com.zql.hi.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient     // 表明自己是一个服务（client）
@RestController
@Slf4j
@RefreshScope
public class HiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @Value("${version}")
    String version;

    @Autowired
    private IUserService userService;

    @GetMapping("/hi")
    @ApiOperation("根据用户id向用户问好")
    public String home(@RequestParam(value = "id", defaultValue = "forezp") @ApiParam(name = "id",value = "用户id") Integer id) {
        User user = userService.getById(id);
        log.info("查询到的用户信息：" + user);
        return "hi " + user.getUsername() + " ,i am from port:" + port + " this version is " + version;
    }

}
