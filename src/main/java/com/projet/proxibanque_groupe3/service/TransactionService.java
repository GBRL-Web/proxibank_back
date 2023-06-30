package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.exceptions.NotFoundException;
import com.projet.proxibanque_groupe3.model.Transaction;
import com.projet.proxibanque_groupe3.persistance.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public List<Transaction> getTransactionFromDatabase(String flag) throws NotFoundException {
        if (!flag.equals("monthly") && !flag.equals("weekly")) {
            logger.error("Transactions cannot be retrieved from database. Invalid flag: {}", flag);
            throw new NotFoundException("Invalid flag : " + flag);
        }
        List<Transaction> transactions = new ArrayList<>();
        try {
            if (flag.equals("weekly")) {
                // Récupérer premier jour de la semaine
                LocalDate startWeek = LocalDate.now();
                while (startWeek.getDayOfWeek() != DayOfWeek.MONDAY && startWeek.getDayOfWeek() != DayOfWeek.FRIDAY) {
                    startWeek = startWeek.minusDays(1);
                }
                // Récupérer dernier jour de la semaine
                LocalDate endWeek = LocalDate.now();
                while (endWeek.getDayOfWeek() != DayOfWeek.MONDAY && endWeek.getDayOfWeek() != DayOfWeek.FRIDAY) {
                    endWeek = endWeek.plusDays(1);
                }
                transactions = transactionRepository.getAllByDateBetween(startWeek, endWeek);
            } else {
                // Récupérer le premier jour du mois
                LocalDate startMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
                // Récupérer le dernier jour du mois
                LocalDate endMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
                transactions = transactionRepository.getAllByDateBetween(startMonth, endMonth);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("Transactions recovered with flag: {} .", flag);
        return transactions;
    }

    public void createTransaction(Integer accountNumber, String description, Float d) {
        Transaction transaction = new Transaction(LocalDate.now(), accountNumber, description, d);
        transactionRepository.save(transaction);
    }
}
