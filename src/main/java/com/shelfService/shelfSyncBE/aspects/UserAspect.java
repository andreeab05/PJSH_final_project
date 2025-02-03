package com.shelfService.shelfSyncBE.aspects;

import com.shelfService.shelfSyncBE.events.user.CreateUserEvent;
import com.shelfService.shelfSyncBE.events.user.DeleteUserEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAspect {
    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("execution(* com.shelfService.shelfSyncBE.service.UserService.createReader(..))")
    public void anyCreateReader() {
    }

    @Pointcut("execution(* com.shelfService.shelfSyncBE.service.UserService.createAuthor(..))")
    public void anyCreateAuthor() {
    }

    @Pointcut("execution(* com.shelfService.shelfSyncBE.service.UserService.deleteUserByUid(..))")
    public void anyDeleteUser() {
    }

    @Before("anyCreateReader()")
    public void beforeCreateReader(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String username = (String) args[0];
        String description = (String) args[1];

        applicationContext.publishEvent(new CreateUserEvent(this, username, description, "Reader"));
    }

    @Before("anyCreateAuthor()")
    public void beforeCreateAuthor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String username = (String) args[0];
        String description = (String) args[1];

        applicationContext.publishEvent(new CreateUserEvent(this, username, description, "Author"));
    }

    @After("anyDeleteUser()")
    public void afterDeleteUser(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Integer uid = (Integer) args[0];

        applicationContext.publishEvent(new DeleteUserEvent(this, uid));
    }
}
