package com.example.example18.advices;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.Duration;
import java.time.Instant;
@Slf4j
@Aspect
public class LoggerAdvice {

    @Around("execution(* com.example.example18..*.*(..))")
    public Object log (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
      log.info(proceedingJoinPoint.getSignature().toString()+" method execution start !");
       Instant start= Instant.now();

      Object object= proceedingJoinPoint.proceed();
        Instant end=Instant.now();
        long timeElapsed= Duration.between(start,end).toMillis();
        log.info("Execution time = "+timeElapsed);
        log.info(proceedingJoinPoint.getSignature().toString()+" method execution end !");
        return object;
    }

    @AfterThrowing(value = "execution(* com.example.example18.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }

}
