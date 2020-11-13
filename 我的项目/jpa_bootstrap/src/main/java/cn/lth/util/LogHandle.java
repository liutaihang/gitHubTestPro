package cn.lth.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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

    @Pointcut("@annotation(cn.lth.util.ProLog)")
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
    public void inBefore(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
//        Object[] args = joinPoint.getArgs();
//        System.out.println(Arrays.toString(args));

        String methodName = joinPoint.getSignature().getName();
        String ClassName = joinPoint.getTarget().getClass().getName();
        Object[] parameterType = joinPoint.getArgs();
        Class[] clazzs = new Class[parameterType.length];
        for (int i = 0; i < parameterType.length; i++) {
            clazzs[i] = parameterType[i].getClass();
        }
        Class clazz = Class.forName(ClassName);
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            if(m.getName().equals(methodName)) {
                Class[] Types = m.getParameterTypes();
                if(Types.length == clazzs.length){
                    ProLog annotation = m.getAnnotation(ProLog.class);
                    String name = annotation.name();
                    String value = annotation.value();
                    String type = annotation.logType().getName();
                    System.out.println(name + " -[" + value + "]- " + type);
                }
            }

        }
    }
}
