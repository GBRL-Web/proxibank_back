package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.model.Transaction;
import com.projet.proxibanque_groupe3.persistance.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTransactionFromDatabaseWeekly() throws Exception {
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction(LocalDate.now().minusDays(4), 1234, "Test transaction 1", 100.0f));
        expectedTransactions.add(new Transaction(LocalDate.now().minusDays(3), 5678, "Test transaction 2", 200.0f));
        when(transactionRepository.getAllByDateBetween(nullable(LocalDate.class), nullable(LocalDate.class))).thenReturn(expectedTransactions);
        List<Transaction> actualTransactions = transactionService.getTransactionFromDatabase("weekly");
        verify(transactionRepository, times(1)).getAllByDateBetween(nullable(LocalDate.class), nullable(LocalDate.class));
        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    void testGetTransactionFromDatabaseMonthly() throws Exception {
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction(LocalDate.now().minusDays(10), 1234, "Test transaction 1", 100.0f));
        expectedTransactions.add(new Transaction(LocalDate.now().minusDays(2), 5678, "Test transaction 2", 200.0f));
        when(transactionRepository.getAllByDateBetween(nullable(LocalDate.class), nullable(LocalDate.class))).thenReturn(expectedTransactions);
        List<Transaction> actualTransactions = transactionService.getTransactionFromDatabase("monthly");
        verify(transactionRepository, times(1)).getAllByDateBetween(nullable(LocalDate.class), nullable(LocalDate.class));
        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    void testGetTransactionFromDatabaseInvalidFlag() {
        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.getTransactionFromDatabase("invalid");
        });
        assertEquals("Flag invalide.", exception.getMessage());
    }

    @Test
    void testCreateTransaction() {
        Transaction expectedTransaction = new Transaction(LocalDate.now(), 1234, "Test transaction", 100.0f);
        when(transactionRepository.save(nullable(Transaction.class))).thenReturn(expectedTransaction);
        transactionService.createTransaction(1234, "Test transaction", 100.0f);
        verify(transactionRepository, times(1)).save(nullable(Transaction.class));
    }
}