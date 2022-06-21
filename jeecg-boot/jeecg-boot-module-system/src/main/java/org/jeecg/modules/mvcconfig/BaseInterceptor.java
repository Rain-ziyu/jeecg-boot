package org.jeecg.modules.mvcconfig;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class BaseInterceptor implements HandlerInterceptor {
    /**
     * 请求方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在进入这个拦截器之前，对跨域提供支持
        if (responseCors(response, request)) {
            return false;
        }
        return true;
    }

    /**
     * 在进入这个拦截器之前, 对跨域提供支持
     * @param response
     * @param request
     * @return
     */
    private boolean responseCors(HttpServletResponse response, HttpServletRequest request) {
        // 判断是否是预检请求
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            // response.setHeader("Cache-Control","no-cache");
            response.setHeader("Access-control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
            response.setHeader("Access-Control-Allow-Headers", "*");
            // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        return false;
    }

}
