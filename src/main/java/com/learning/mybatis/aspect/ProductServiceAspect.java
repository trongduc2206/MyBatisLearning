package com.learning.mybatis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class ProductServiceAspect {

    @Before(value = "execution(* com.learning.mybatis.service.impl.ProductServiceImpl..*(..)) && args(productId)")
    public void beforeAdvice(JoinPoint joinPoint, int productId) {
        System.out.println("Before method:" + joinPoint.getSignature());

        System.out.println("Start processing Product with id: " + productId);
    }

    @After(value = "execution(* com.learning.mybatis.service.impl.ProductServiceImpl..*(..)) && args(productId)")
    public void afterAdvice(JoinPoint joinPoint, int productId) {
        System.out.println("After method:" + joinPoint.getSignature());

        System.out.println("Processing Successfully Product with id: " + productId);
    }


}
