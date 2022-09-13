package org.pj.metaverse.aop;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import org.pj.metaverse.annotation.EventLogAnnotation;
import org.pj.metaverse.entity.BaseLogEventRecord;
import org.pj.metaverse.enums.EventEnum;
import org.pj.metaverse.utils.RedisPublisher;
import org.pj.metaverse.utils.ServerUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author pengjie
 * @date 16:21 2022/5/25
 **/
@Aspect
@Component
@Slf4j
@Order(1)
public class LogOperationAop {
    @Resource
    private ServerUtils utils;
    @Resource
    private RedisPublisher publisher;
    /**
     * 定义了一个切入点
     * @author pengjie
     * @date 2022/5/25 16:23
     */
    @Pointcut("@annotation(org.pj.metaverse.annotation.EventLogAnnotation)")
    public void methodAspect() {
    }

    /**
     * 定义了一个前置通知，这个通知对刚刚上面我们定义的切入点中的所有方法有效
     * @author pengjie
     * @date 2022/5/25 16:23
     * @param joinPoint
     */
    @Before("methodAspect() && @annotation(eventLogAnnotation)")
    public void doAudit(JoinPoint joinPoint, EventLogAnnotation eventLogAnnotation) throws NoSuchMethodException {
        // 目标对象
        Class<?> clazz = joinPoint.getTarget().getClass();
        // 方法签名
        String method = joinPoint.getSignature().getName();
        // 方法参数
        Object[] thisArgs = joinPoint.getArgs();
        // 参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        // 方法
        Method thisMethod = clazz.getMethod(method, parameterTypes);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String remoteHost = this.getRemoteHost(request);
        // 自定义日志接口，检查指定的对象引用不为空
        EventEnum eventEnum = eventLogAnnotation.EVENT_ENUM();
        String s = JSON.toJSONString(thisArgs);
        Object defaultNull = StpUtil.getLoginIdDefaultNull();
        String userId = null;
        if (defaultNull != null){
            userId = (String) defaultNull;
        }
        // 将参数封装到对象中
        BaseLogEventRecord build = new BaseLogEventRecord(eventEnum,utils.serverName,remoteHost,s,userId);
        // 将日志通过redis发布到对应类型
        publisher.sendMsg(build);
    }
    private String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
    }


    @After(value = "methodAspect()")
    public void after() {
//        System.out.println("After advice");
    }

    @AfterReturning(value = "methodAspect() && @annotation(eventLogAnnotation)",returning = "methodResult")
    public void afterReturning(JoinPoint joinPoint, EventLogAnnotation eventLogAnnotation, Object methodResult) {
        EventEnum eventEnum = eventLogAnnotation.EVENT_ENUM();
        switch (eventEnum){
            case USER_LOGIN -> this.loginAfter(methodResult);
        }
    }

    private void loginAfter(Object methodResult){

    }

    @AfterThrowing(value = "methodAspect()")
    public void afterThrowing() {
//        System.out.println("AfterThrowing advice");
    }

    @Around("methodAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("环绕通知开始");
        System.out.println("目标对象 = " + proceedingJoinPoint.getTarget());
        System.out.println("方法签名 = " + proceedingJoinPoint.getSignature());
        System.out.println("方法参数 = " + Arrays.toString(proceedingJoinPoint.getArgs()));
        //放行
        Object proceed=proceedingJoinPoint.proceed();
        System.out.println( "目标方法执行完毕");
        return proceed;
    }
}
