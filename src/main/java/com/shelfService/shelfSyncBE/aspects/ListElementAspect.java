package com.shelfService.shelfSyncBE.aspects;

import com.shelfService.shelfSyncBE.entity.Book;
import com.shelfService.shelfSyncBE.entity.ListElement;
import com.shelfService.shelfSyncBE.events.progress.UpdateElementEvent;
import com.shelfService.shelfSyncBE.repository.BookRepository;
import com.shelfService.shelfSyncBE.repository.ListElementRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Aspect
@Configuration
public class ListElementAspect {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ListElementRepository listElementRepository;
    @Autowired
    private BookRepository bookRepository;

    @Pointcut("execution(* com.shelfService.shelfSyncBE.service.ListElementService.updateElement(..))")
    public void anyUpdateElement() {
    }

    @Before("anyUpdateElement()")
    public void afterSuccessfulUpdateElement(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer elementId = (Integer) args[0];
        String newProgress = (String) args[1];
        Integer currentPages = (Integer) args[2];
        Optional<ListElement> optionalElement = listElementRepository.findById(elementId);
        if (optionalElement.isEmpty())
            return;

        ListElement listElement = optionalElement.get();
        String oldProgress = listElement.getProgress();
        Integer totalPages = listElement.getBook().getPages();
        applicationContext.publishEvent(
                new UpdateElementEvent(this, elementId,listElement.getBook().getTitle(), oldProgress, newProgress, currentPages, totalPages)
        );
    }
}
