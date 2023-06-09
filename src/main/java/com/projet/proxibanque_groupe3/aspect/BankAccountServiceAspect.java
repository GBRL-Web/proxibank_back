package com.projet.proxibanque_groupe3.aspect;

import com.projet.proxibanque_groupe3.ProxibanqueGroupe3Application;
import com.projet.proxibanque_groupe3.model.Transfert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BankAccountServiceAspect {

    private Logger logger = LoggerFactory.getLogger(ProxibanqueGroupe3Application.class);

    @Around("execution(* com.projet.proxibanque_groupe3.service.BankAccountService.makeTransfert(..)) &&" +
            "args(transfert,..)")
    public void logTransfert(ProceedingJoinPoint joinPoint, Transfert transfert) throws Throwable {
        joinPoint.proceed();
        logger.warn("Transfert du compte n°" + transfert.getAccountNumberDebited() + " au compte n°" + transfert.getAccountNumberCredited() + " et d'un montant de " + transfert.getAmount() + " euros.");
    }
}