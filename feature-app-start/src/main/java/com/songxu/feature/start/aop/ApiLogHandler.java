package com.songxu.feature.start.aop;

import com.alibaba.fastjson.JSON;
import com.songxu.feature.api.model.Result;
import com.songxu.feature.api.model.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author songxu
 * @version 1.0.0
 * @date 2021-04-11 11:15:32
 */
@Aspect
@Component
public class ApiLogHandler {

    private final Logger logger = LoggerFactory.getLogger("AccessLog");

    @Around("@annotation(apiLogMethod)")
    public Object handleRequestAppMethod(ProceedingJoinPoint pjp, ApiLogMethod apiLogMethod) {
        long t1 = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        String className = pjp.getTarget().getClass().getName();
        String methodName = signature.getMethod().getName();

        Object[] args = pjp.getArgs();

        Object resultObj = null;
        try {
            resultObj = pjp.proceed();
            return resultObj;
        } catch (CommonException commonException) {
            resultObj = Result.panic(commonException.getMessage());
            return resultObj;
        } catch (Throwable throwable) {
            StringBuilder exceptionBuilder = new StringBuilder();
            exceptionBuilder.append(className).append("||")
                    .append(methodName).append("||")
                    .append(JSON.toJSONString(args)).append("||");
            logger.error(exceptionBuilder.toString(), throwable);
            resultObj = Result.panic(throwable.getMessage());

        } finally {
            @SuppressWarnings("rawtypes")
            Result result = (Result) resultObj;

            StringBuilder logBuilder = new StringBuilder();
            String input = StringUtils.EMPTY;
            String ret = StringUtils.EMPTY;

            if (apiLogMethod.logArgs()) {
                input = JSON.toJSONString(args);
            }
            if (apiLogMethod.logRetValue()) {
                ret = JSON.toJSONString(result.getData());
            }
            long t2 = System.currentTimeMillis();

            long rt = t2 - t1;
            if (rt < 1) {
                rt = 1;
            }

            logBuilder.append(className).append("||")
                    .append(methodName).append("||")
                    .append(input).append("||")
                    .append(ret).append("||")
                    .append(result.getCode()).append("||")
                    .append(result.getErrorMsg()).append("||")
                    .append(rt);

            logger.info(logBuilder.toString());
        }

        return resultObj;

    }
}
