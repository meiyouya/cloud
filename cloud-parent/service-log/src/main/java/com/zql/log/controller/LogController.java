package com.zql.log.controller;

import com.zql.log.annotation.Logs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @Logs("访问首页")
    public String index() {
        return "index";
    }
}
