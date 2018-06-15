package cn.lth.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author liutaihang
 * @Title: jpa_bootstrap
 * @Package cn.lth.util
 * @Description: ${todo}
 * @date 2018/6/1415:47
 */
@Component
@Aspect
@Slf4j
public class LogHandle {

    @Pointcut("@annotation(cn.lth.util.DemoLog)")
    private void cut(){}

    @Before("cut()")
    public void inAfter(JoinPoint joinPoint){
//        Object[] args = joinPoint.getArgs();
//        System.out.println("args --> " + Arrays.toString(args));
    }

    @Around("cut()")
    public void inAround(ProceedingJoinPoint joinPoint){
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Before("cut()")
    public void inBefore(JoinPoint joinPoint){
//        Object[] args = joinPoint.getArgs();
//        System.out.println(Arrays.toString(args));
    }
}
