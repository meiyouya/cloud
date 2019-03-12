package com.zql.service.feign.controller;

import com.zql.service.feign.clients.HiService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private HiService hiService;

    @GetMapping("/hi")
    @ApiOperation(value = "调用hi服务")
    public String sayHi(@RequestParam @ApiParam(name = "name",value = "用户名") String name) {
        return hiService.sayHi(name);
    }
}
