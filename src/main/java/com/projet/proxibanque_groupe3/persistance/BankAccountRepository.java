package com.projet.proxibanque_groupe3.persistance;

import com.projet.proxibanque_groupe3.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<Set<BankAccount>> getBankAccountsByClient_Id(Long id);
    Optional<BankAccount> getBankAccountByAccountNumber(Integer accountNumber);
}
