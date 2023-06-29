package com.projet.proxibanque_groupe3.aspect;

import com.projet.proxibanque_groupe3.model.Transfer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BankAccountServiceAspect {

    private Logger logger = LoggerFactory.getLogger(BankAccountServiceAspect.class);

    @Around("""
            execution(* com.projet.proxibanque_groupe3.service.BankAccountService.makeTransfer(..)) &&\
            args(transfer,..)\
            """)
    public void logTransfer(ProceedingJoinPoint joinPoint, Transfer transfer) throws Throwable {
        joinPoint.proceed();
        logger.warn("Transfer from account n° {} to account n° {} of {} euros.", transfer.getFromAccount(), transfer.getToAccount(), transfer.getAmount());
    }
}
