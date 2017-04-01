package com.example.config;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.util.resources.cldr.my.CalendarData_my_MM;

/**
 * @Package: com.example.config
 * @Description: MyLog的实现类,拦截注解
 * @author: liuxin
 * @date: 17/2/23 下午4:22
 */
@Aspect
@Component
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.example.config.MyLog)")
    private void cut() {
    }

    /**
     * 定制一个环绕通知
     *
     * @param pjp
     */
    @Around("cut()")
    public void advice(ProceedingJoinPoint pjp) {
        logger.info("===================环绕通知之开始=================");
        try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        Class<?> classTarget = pjp.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        logger.info("当前执行类:{},执行方法:{}", className, methodName);
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Object o : args) {
            sb.append(o).append(",");
        }
        sb.substring(0, sb.length()-1);
        sb.append("]");
        logger.info("参数:{}",sb.toString());
        logger.info("===================环绕通知之结束=================");
    }

    @Before("cut()&&@annotation(myLog)")
    public void record(JoinPoint joinPoint, MyLog myLog) {
        logger.info(myLog.value());
    }
}
