package com.nozomi.aop;

import com.alibaba.fastjson.JSONObject;
import com.nozomi.mapper.OperateLogMapper;
import com.nozomi.pojo.OperateLog;
import com.nozomi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.nozomi.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable{
        //1.获取操作人id，获得jwt令牌，解析令牌
        String jwt=request.getHeader("token");
        Claims claims= JwtUtils.JwtParse(jwt);
        Integer operateUser=(Integer) claims.get("id");

        //2.获取操作时间
        LocalDateTime operateTime=LocalDateTime.now();

        //3.获取操作类名
        String className=joinPoint.getTarget().getClass().getName();

        //4.获取操作方法名
        String methodName=joinPoint.getSignature().getName();

        //5.获取操作方法参数
        Object[] args=joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        Long begin = System.currentTimeMillis();

        //6.执行目标方法同时获取返回值
        Object result=joinPoint.proceed();

        Long end = System.currentTimeMillis();

        //7.转json
        String returnValue = JSONObject.toJSONString(result);

        //8.获取执行时间
        Long costTime = end - begin;

        //9.记录操作日志
        OperateLog operateLog=new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);

        //10.将日志记录到数据库中
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}", operateLog);

        return result;

    }
}
