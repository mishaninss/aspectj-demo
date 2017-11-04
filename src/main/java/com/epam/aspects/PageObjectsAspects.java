package com.epam.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.stqa.selenium.factory.WebDriverPool;

import java.util.Arrays;

@Aspect
public class PageObjectsAspects {
	
	@Pointcut("call(public void com.epam.pages..*.* (..))")
	public void anyPageObjectMethod() {
		//Тут ничего писать не надо
	}

    @Pointcut("call(public !void com.epam.pages..*.* (..))")
    public void notVoidPageObjectMethod() {
        //Тут ничего писать не надо
    }

	@Before("anyPageObjectMethod()")
	public void logMethod(JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String message = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0){
            message += " " + Arrays.toString(args);
        }
        log.info(message);
    }

    @AfterReturning(value = "notVoidPageObjectMethod()", returning = "ret")
    public void logReturnMethod(Object ret, JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String message = joinPoint.getSignature().getName() + " returned " + ret;
        log.info(message);
    }

    /*@After("notVoidPageObjectMethod()")
    public void logAfterMethod(JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String message = joinPoint.getSignature().getName() + " finished";
        log.info(message);
    }*/

    @Around("notVoidPageObjectMethod()")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String message = joinPoint.getSignature().getName() + " around started";
        log.info(message);
        Object ret = joinPoint.proceed();
        message = joinPoint.getSignature().getName() + " around finished";
        log.info(message);
        if (ret != null) {
            //throw new IllegalArgumentException("from advice");
        }
        return "another string";
    }

    @AfterThrowing(value = "anyPageObjectMethod()", throwing = "ex")
    public void logPageDetailsIfException(Exception ex, JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        WebDriver webDriver = WebDriverPool.DEFAULT.getDriver("chrome");
        log.info("URL: {}", webDriver.getCurrentUrl());
        log.info("Title: {}", webDriver.getTitle());
    }

}
