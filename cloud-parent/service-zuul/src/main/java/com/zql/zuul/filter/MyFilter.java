/*
package com.zql.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class MyFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info("请求路径：" + request.getRequestURL() + "，请求方法：" + request.getMethod());
        String accessToken = request.getParameter("token");
        if (StringUtils.isEmpty(accessToken)) {
            log.info("token是空");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                currentContext.getResponse().getWriter().write("token is empty");
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        log.info("ok");
        return null;
    }
}
*/
