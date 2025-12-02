package com.makerspace.makerspaceapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Log all methods in controller package
    @Pointcut("execution(* com.makerspace.makerspaceapp.controller..*(..))")
    public void controllerMethods() {}

    // Log all methods in service package
    @Pointcut("execution(* com.makerspace.makerspaceapp.service..*(..))")
    public void serviceMethods() {}

    // Before method execution
    @Before("controllerMethods() || serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.debug("Entering method: {} with arguments: {}", 
                    joinPoint.getSignature().toShortString(),
                    Arrays.toString(joinPoint.getArgs()));
    }

    // After method execution
    @AfterReturning(pointcut = "controllerMethods() || serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.debug("Method {} returned: {}", 
                    joinPoint.getSignature().toShortString(), 
                    result);
    }

    // After throwing exception
    @AfterThrowing(pointcut = "controllerMethods() || serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method {} threw exception: {}", 
                    joinPoint.getSignature().toShortString(), 
                    exception.getMessage());
    }

    // Around method execution (includes timing)
    @Around("controllerMethods() || serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        logger.info("Starting method: {}", joinPoint.getSignature().toShortString());
        
        Object result = joinPoint.proceed();
        
        long endTime = System.currentTimeMillis();
        logger.info("Method {} executed in {} ms", 
                   joinPoint.getSignature().toShortString(), 
                   (endTime - startTime));
        
        return result;
    }
}