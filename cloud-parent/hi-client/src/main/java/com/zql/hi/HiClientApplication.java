package com.zql.hi;

import brave.sampler.Sampler;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zql.hi.entity.User;
import com.zql.hi.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient     // 表明自己是一个服务（client）
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
@Slf4j
@RefreshScope   // 开启配置刷新
@EnableHystrix  // 开启断路器
@EnableHystrixDashboard     // 开启断路器监控
public class HiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiClientApplication.class, args);
    }

//    @Value("${server.port}")
//    String port;

//    @Value("${version}")
//    String version;

    @Autowired
    private IUserService userService;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/hi")
    @ApiOperation("根据用户id向用户问好")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "id", defaultValue = "forezp") @ApiParam(name = "id",value = "用户id") Integer id) {
        User user = userService.getById(id);
        log.info("查询到的用户信息：" + user);
//        return "hi " + user.getUsername() + " ,i am from port:" + port + " this version is " + version;
        return "hi " + user.getUsername();
    }

    private String hiError(Integer id) {
        return "query error";
    }

    @RequestMapping("/hi")
    public String callHome(){
        log.info("calling trace service-hi  ");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }
    @RequestMapping("/info")
    public String info(){
        log.info("calling trace service-hi ");
        return "i'm service-hi";

    }

    @Bean
    public Sampler sampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
