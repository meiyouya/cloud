package com.zql.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.containsKey("error.status_code");
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        log.info("请求路径：" + context.getRequest().getRequestURL());
        Throwable throwable = context.getThrowable();
        log.error("ErrorFilter 抓到一个错误:{}",throwable.getCause().getMessage());
        context.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        context.set("error.exception",throwable.getCause());
        return null;
    }
}
