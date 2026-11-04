package com.danilpopov.taskmanager.infrastructure.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLoggingAspect {
    @Before("execution(* com.danilpopov.taskmanager.presentation.controller.*.*(..))")
    public void logRequestBody(JoinPoint joinPoint){
        System.out.println("Вызван метод: " + joinPoint.getSignature().getName() +
                "Класса: " + joinPoint.getTarget().getClass().getName() );
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("Аргументы:" + arg + "\n");
        }

    }
}
