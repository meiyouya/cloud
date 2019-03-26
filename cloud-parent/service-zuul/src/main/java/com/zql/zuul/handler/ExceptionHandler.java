package com.zql.zuul.handler;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler extends DefaultErrorWebExceptionHandler {


    public ExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                            ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 获取异常属性
     * @param request
     * @param includeStackTrace
     * @return
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        int code = 500;
        Throwable error = super.getError(request);
        return response(code,this.buildMessage(request,error));
    }

    /**
     *  指定响应处理方法为json
     * @param errorAttributes
     * @return
     */
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(),this::renderErrorResponse);
    }

    /**
     * 根据code得到HttpStatus对象
     * @param errorAttributes
     * @return
     */
    protected HttpStatus getHttpStatus(Map<String,Object> errorAttributes) {
        int statusCode = (int) errorAttributes.get("code");
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 构建异常信息
     * @param request
     * @param error
     * @return
     */
    private String buildMessage(ServerRequest request, Throwable error) {
        StringBuilder message = new StringBuilder("failed to handler request[");
        message.append(request.method());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (error != null) {
            message.append(": ");
            message.append(error.getMessage());
        }
        return message.toString();
    }

    /**
     * 构建返回的json数据
     * @param code
     * @param errorMessage
     * @return
     */
    private Map<String, Object> response(int code, String errorMessage) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);
        map.put("message",errorMessage);
        map.put("data",null);
        return map;
    }
}
