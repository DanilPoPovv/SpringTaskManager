package com.danilpopov.taskmanager.infrastructure.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.danilpopov.taskmanager.application.*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Имя класса: " + joinPoint.getTarget().getClass().getName());
        System.out.println("Имя метода: " + joinPoint.getSignature().getName());
        long startDate = System.nanoTime();
        Object result;
        try {
            result = joinPoint.proceed();
        }
        catch (RuntimeException ex) {
            System.out.println("Возникло непроверяемое исключение: " + ex.getClass().getName());
            throw ex;
        }catch (Throwable ex) {
            System.out.println("Возникло проверяемое исключение: " + ex.getClass().getName());
            throw ex;
        }
        finally {
            long endDate =  System.nanoTime();
            System.out.println("Время выполнения метода = " + ((endDate - startDate) / 1_000_000));
        }
        return result;
    };
}
