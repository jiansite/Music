package com.jester.music.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 请求拦截
 *
 * @author Jester
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 12:42
 * @version version 1.0.0
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Autowired
    RedisTemplate redisTemplate;

    public int getOrder() {
        return 0;
    }

    public String getPathPatterns() {
        return "/app/**";
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        logger.info(String.format("url:%s,ip:%s、%s",request.getRequestURI(),request.getRemoteAddr(),request.getHeader("X-Forwarded-For")));
    }
}
