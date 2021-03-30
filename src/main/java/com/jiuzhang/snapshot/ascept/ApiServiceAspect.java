package com.jiuzhang.snapshot.ascept;

import com.jiuzhang.snapshot.exception.Errors;
import com.jiuzhang.snapshot.exception.IError;
import com.jiuzhang.snapshot.exception.TradeSnapshotException;
import com.jiuzhang.snapshot.result.PlainResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Date 2021/2/28 14:52
 * @Author FU
 */
@Slf4j
@Aspect
@Component
public class ApiServiceAspect {

    @Pointcut(value = "@annotation(com.jiuzhang.snapshot.annotation.ApiAnnotation)")
    public void apiServiceAspect() {

    }

    @Around(value = "@annotation(com.jiuzhang.snapshot.annotation.ApiAnnotation)")
    public Object around(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method m = methodSignature.getMethod();

        String methodInfo = String.format("[method: %s]", m.getName());

        Long start = System.currentTimeMillis();

        try {
            Object obj = point.proceed();
            Long end = System.currentTimeMillis();
            log.info(methodInfo + "cost:" + (end-start) + " ms.");

            return obj;
        } catch (TradeSnapshotException tse) {
            log.error("Failed" + methodInfo + " " + tse.getMessage(), tse);
            IError error = tse.getError();
            return PlainResult.buildFailed(error.getErrorCode(), error.getErrorMessage());
        } catch (Throwable throwable) {
            log.error("Failed" + methodInfo + " " + throwable.getMessage(), throwable);
            return PlainResult.buildFailed(Errors.ServerError.getErrorCode(), Errors.ServerError.getErrorMessage());
        }
    }

}
