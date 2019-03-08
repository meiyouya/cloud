package com.zql.service.feign.clients;

import org.springframework.stereotype.Component;

@Component
public class SchedualHiServiceHystrix implements HiService {

    @Override
    public String sayHi(String name) {
        return "sorry " + name + "， we get a error";
    }
}
