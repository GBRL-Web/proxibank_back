package com.projet.proxibanque_groupe3.persistance;

import com.projet.proxibanque_groupe3.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getAllByDateBetween(LocalDate start, LocalDate end);
}
