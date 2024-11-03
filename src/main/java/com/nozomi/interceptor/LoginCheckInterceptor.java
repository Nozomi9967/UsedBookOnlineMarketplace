package com.nozomi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.nozomi.pojo.Result;
import com.nozomi.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求url
        String url = request.getRequestURI().toString();

        //2.判断请求的url中是否包含login，如果包含，放行
        if(url.contains("login")){
            return true;
        }

        //3.获取请求头中的jwt令牌(token)
        String jwt=request.getHeader("token");

        //4.判断jwt令牌是否为空，如果为空，拦截，否则放行
        if(!StringUtils.hasLength(jwt)){
            log.info("jwt为空，拦截！");
            Result error= Result.fail("NOT_LOGIN");
            String notLogin= JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        //5.解析jwt令牌
        try {
            JwtUtils.JwtParse(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            //解析失败，拦截
            log.info("解析jwt失败，拦截！");
            Result error= Result.fail("NOT_LOGIN");
            String notLogin= JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        //6.放行
        log.info("令牌合法，放行！");
        return true;

    }
}
