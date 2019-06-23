package com.jester.music.interceptor;

import com.alibaba.fastjson.JSON;
import com.jester.music.mode.vo.UserSession;
import com.jester.music.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @className: HttpUtil
 * @description: HttpUtil
 * @author: Jester
 * @email: shujian.jiansite@gmail.com
 * @date: 2019-06-23 12:42
 * @version: version 1.0.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(UserSession.class);
    @Autowired
    RedisTemplate redisTemplate;

    public int getOrder() {
        return 0;
    }

    public String getPathPatterns() {
        return "/app/login/";
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,token");
        response.setHeader("Access-Control-Expose-Headers", "*");
        //获取RequestHeader里的userToken
        String userToken = request.getHeader("token");

        UserSession.set("token", userToken);

        return beginControlErupt(userToken, request.getRequestURI(), response);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.debug("clean UserThreadLocal...");
        UserSession.clear();
    }

    public boolean beginControlErupt(String uniqueId, String uri, HttpServletResponse response) {
        Boolean hasKey = redisTemplate.hasKey(uri+uniqueId);
        if (hasKey) {
            Result result = Result.error(20004, "访问频繁");
            this.writer(response, result, null);
            return false;
        } else {
            return true;
        }
    }

    public void writer(HttpServletResponse response, Result result, String sLog) {
        logger.info(result.toString());
        String toWrite = JSON.toJSONString(result);
        this.writer(response, toWrite, sLog);
    }

    private void writer(HttpServletResponse response, String str, String sLog) {
        PrintWriter out = null;

        try {
            logger.info(sLog);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain; charset=utf-8");
            out = response.getWriter();
            out.write(str);
        } catch (Exception e) {
            logger.error("Exception", e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                logger.error("Exception", e.getMessage());
            }

            logger.error(str);
            if (UserSession.isNull()) {
                UserSession.clear();
            }

        }

    }
}
