package org.pj.metaverse.aop;

import de.huxhorn.sulky.ulid.ULID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

/**
 * @author pengjie
 * @date 15:04 2022/6/15
 **/
@Aspect
@Component
public class SaveInjectionAop {
    @Resource
    private ULID ulid;

    @Pointcut("execution(* org.springframework.data.repository.CrudRepository.save(..))")
    public void saveInjection() {
    }

    @Around("saveInjection()")
    public Object beforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知的目标方法名：" + joinPoint.getSignature().getName());
        //修改传入参数
        processInputArg(joinPoint.getArgs());
        return joinPoint.proceed();
    }

    /**
     * 处理输入参数
     *
     * @param args 入参列表
     */
    private void processInputArg(Object[] args) {
        for (Object arg : args) {
            Object id = this.invokeGet(arg, "id");
            if (id == null) {
                this.invokeSet(arg, "id", this.ulid.nextULID());
            }
        }
    }

    /**
     * java反射bean的set方法
     *
     * @param objectClass objectClass
     * @param fieldName fieldName
     * @return Method
     * @throws RuntimeException
     */
    private Method getSetMethod(Class<?> objectClass, String fieldName) {
        try {
            Class<?> superclass = objectClass.getSuperclass();
            Class<?>[] parameterTypes = new Class<?>[1];
            Field field = superclass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase(Locale.ROOT));
            sb.append(fieldName.substring(1));
            return superclass.getMethod(sb.toString(), parameterTypes);
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            throw new RuntimeException("Reflect error!");
        }
    }
    /**
     * 执行set方法
     *
     * @param obj 执行对象
     * @param fieldName 属性
     * @param value 值
     * @throws RuntimeException
     */
    private void invokeSet(Object obj, String fieldName, Object value) {
        Method method = getSetMethod(obj.getClass(), fieldName);
        try {
            method.invoke(obj, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Reflect error!");
        }
    }

    /**
     * java反射bean的get方法
     *
     * @param objectClass objectClass
     * @param fieldName fieldName
     * @return Method
     * @throws RuntimeException
     */
    private Method getGetMethod(Class<?> objectClass, String fieldName) {
        try {
            Class<?> superclass = objectClass.getSuperclass();
            StringBuilder sb = new StringBuilder();
            sb.append("get");
            sb.append(fieldName.substring(0, 1).toUpperCase(Locale.ROOT));
            sb.append(fieldName.substring(1));
            return superclass.getMethod(sb.toString());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Reflect error!");
        }
    }
    /**
     * 执行get方法
     *
     * @param obj 执行对象
     * @param fieldName 属性
     * @throws RuntimeException
     */
    private Object invokeGet(Object obj, String fieldName) {
        Method method = getGetMethod(obj.getClass(), fieldName);
        try {
            return method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Reflect error!");
        }
    }
}
