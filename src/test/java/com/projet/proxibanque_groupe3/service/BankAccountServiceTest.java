package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.model.BankAccount;
import com.projet.proxibanque_groupe3.model.CheckingAccount;
import com.projet.proxibanque_groupe3.model.Transfer;
import com.projet.proxibanque_groupe3.persistance.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private BankAccountService bankAccountService;

    @Test
    void getBankAccountsByIdClientFromDatabase_shouldReturnEmptyOptional_whenRepositoryReturnsEmptyOptional() {
        // Arrange
        when(bankAccountRepository.getByClientId(anyLong())).thenReturn(Optional.empty());

        // Act
        Optional<Set<BankAccount>> bankAccounts = bankAccountService.getBankAccountsByIdClientFromDatabase(123);

        // Assert
        assertThat(bankAccounts).isEmpty();
    }

    @Test
    void getBankAccountsByIdClientFromDatabase_shouldReturnSetOfBankAccounts_whenRepositoryReturnsSetOfBankAccounts() {
        // Arrange
        BankAccount bankAccount1 = new CheckingAccount();
        bankAccount1.setAccountNumber(111);
        BankAccount bankAccount2 = new CheckingAccount();
        bankAccount2.setAccountNumber(222);
        Set<BankAccount> bankAccountSet = new HashSet<>();
        bankAccountSet.add(bankAccount1);
        bankAccountSet.add(bankAccount2);
        when(bankAccountRepository.getByClientId(anyLong())).thenReturn(Optional.of(bankAccountSet));

        // Act
        Optional<Set<BankAccount>> bankAccounts = bankAccountService.getBankAccountsByIdClientFromDatabase(123);

        // Assert
        assertThat(bankAccounts).isNotEmpty();
        assertThat(bankAccounts.get()).containsExactlyInAnyOrder(bankAccount1, bankAccount2);
    }

    @Test
    void getBankAccountByAccountNumber_shouldReturnEmptyOptional_whenRepositoryReturnsEmptyOptional() {
        // Arrange
        when(bankAccountRepository.getByAccountNumber(anyInt())).thenReturn(Optional.empty());

        // Act
        Optional<BankAccount> bankAccount = bankAccountService.getBankAccountByAccountNumber(123);

        // Assert
        assertThat(bankAccount).isEmpty();
    }

    @Test
    void getBankAccountByAccountNumber_shouldReturnBankAccount_whenRepositoryReturnsBankAccount() {
        // Arrange
        BankAccount bankAccount = new CheckingAccount();
        bankAccount.setAccountNumber(111);
        when(bankAccountRepository.getByAccountNumber(anyInt())).thenReturn(Optional.of(bankAccount));

        // Act
        Optional<BankAccount> returnedBankAccount = bankAccountService.getBankAccountByAccountNumber(123);

        // Assert
        assertThat(returnedBankAccount).isNotEmpty();
        assertThat(returnedBankAccount.get()).isEqualTo(bankAccount);
    }

    @Test
void makeTransfert_shouldThrowException_whenAccountNumbersAreTheSame() {
    // Set up the test data
    Transfer transfert = new Transfer(null, null, null);
    transfert.setFromAccount(12345678);
    transfert.setToAccount(12345678);
    transfert.setAmount(1000f);

    // Call the method and assert that an exception is thrown
    assertThrows(Exception.class, () -> bankAccountService.transferTo(transfert));
}

@Test
void makeTransfert_shouldThrowException_whenDebitedAccountHasInsufficientBalance() {
    // Set up the test data
    CheckingAccount accountDebited = new CheckingAccount();
    accountDebited.setAccountNumber(12345678);
    accountDebited.setBalance(500.0f);
    accountDebited.setOverdraft(1000.0f);

    BankAccount accountCredited = new BankAccount();
    accountCredited.setAccountNumber(87654321);

    Transfer transfert = new Transfer(null, null, null);
    transfert.setFromAccount(accountDebited.getAccountNumber());
    transfert.setToAccount(accountCredited.getAccountNumber());
    transfert.setAmount(2000.0f);

    // Set up the mock for bankAccountRepository.getBankAccountByAccountNumber
    when(bankAccountRepository.getByAccountNumber(accountDebited.getAccountNumber())).thenReturn(Optional.of(accountDebited));

    // Call the method and assert that an exception is thrown
    assertThrows(Exception.class, () -> bankAccountService.transferTo(transfert));
}

@Test
void makeTransfert_shouldMakeTransfert_whenDataIsValid() throws Exception {
    // Set up the test data
    CheckingAccount accountDebited = new CheckingAccount();
    accountDebited.setAccountNumber(12345678);
    accountDebited.setBalance(5000.0f);
    accountDebited.setOverdraft(1000.0f);

    BankAccount accountCredited = new BankAccount();
    accountCredited.setAccountNumber(87654321);
    accountCredited.setBalance(3000.0f);

    Transfer transfert = new Transfer(null, null, null);
    transfert.setFromAccount(accountDebited.getAccountNumber());
    transfert.setToAccount(accountCredited.getAccountNumber());
    transfert.setAmount(2000.0f);

    // Set up the mock for bankAccountRepository.getBankAccountByAccountNumber
    when(bankAccountRepository.getByAccountNumber(accountDebited.getAccountNumber())).thenReturn(Optional.of(accountDebited));
    when(bankAccountRepository.getByAccountNumber(accountCredited.getAccountNumber())).thenReturn(Optional.of(accountCredited));

    // Call the method
    bankAccountService.transferTo(transfert);

    // Assert that the balances of both accounts are updated
    assertEquals(3000.0f, accountDebited.getBalance());
    assertEquals(5000.0f, accountCredited.getBalance());

    // Verify that the transactionService.createTransaction method is called twice
    verify(transactionService, times(2)).createTransaction(anyInt(), nullable(String.class), anyFloat());
}
}