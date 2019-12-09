package com.ix.aspect;

import com.ix.annotation.EmpDeleteCacheAnnotation;
import com.ix.util.ApplicationContextUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Aspect
@Configuration
public class EmpCacheAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.ix.annotation.EmpAddCacheAnnotation)")
    public Object empAddCacheAspect(ProceedingJoinPoint pjp) {
        //获取类名
        String className = pjp.getTarget().getClass().getName();
        //获取方法名
        String methodName = pjp.getSignature().getName();
        //设置参数名为 方法名拼接参数
        String argsName = methodName;
            //获取参数,与方法名拼接
        for (Object arg : pjp.getArgs()) {
            argsName += arg;
        }
        try {
            /**获取缓存中的值
             * 类名为Key
             * 方法名+参数名为key
             */
            if (!redisTemplate.opsForHash().hasKey(className, argsName)) {
                //获取方法执行完毕后的返回数据
                Object proceed = pjp.proceed();
                System.out.println("=="+proceed);
//                System.out.println("方法执行成功");
                //获取对象
                RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
                //存储缓存数据
                redisTemplate.setKeySerializer(new StringRedisSerializer());
                //设置hashkey序列化
                redisTemplate.setHashKeySerializer(new StringRedisSerializer());
                //hash模型
                redisTemplate.opsForHash().put(className,argsName,proceed);

                return proceed;
            }else{
                //如果缓存有值，直接返回redis缓存的值
                return redisTemplate.opsForHash().get(className,argsName);
            }

        } catch (Throwable throwable) {
//            System.out.println("方法执行失败");
            throwable.printStackTrace();
            return null;
        }
    }

    @Around("@annotation(com.ix.annotation.EmpDeleteCacheAnnotation)")
    public Object empDeleteCacheAspect(ProceedingJoinPoint pjp) {
        //获取类名
        String className = pjp.getTarget().getClass().getName();
        try {
            Object proceed = pjp.proceed();
//            System.out.println("方法执行成功");
            redisTemplate.delete(className);
            // 获取注解信息
            System.out.println(pjp.getSignature());
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            EmpDeleteCacheAnnotation empDeleteCacheAnnotation = signature.getMethod().getAnnotation(EmpDeleteCacheAnnotation.class);
            System.out.println(empDeleteCacheAnnotation.value());
            return proceed;
        } catch (Throwable throwable) {
//            System.out.println("方法执行失败");
            throwable.printStackTrace();
            return null;
        }
    }

}
