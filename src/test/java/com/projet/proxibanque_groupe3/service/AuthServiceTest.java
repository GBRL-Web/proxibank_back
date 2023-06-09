package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.model.AuthInfos;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.persistance.AuthRepository;
import com.projet.proxibanque_groupe3.persistance.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private AuthRepository authRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private AuthService authService;

    private Employee employee;
    private AuthInfos authInfos;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = new Employee();
        employee.setName("John");
        employee.setSurname("Doe");

        authInfos = new AuthInfos();
        authInfos.setLogin("djohn");
        authInfos.setPassword("password");
        authInfos.setEmployee(employee);
    }

    @Test
    public void testCreateAuthInfos() {
        AuthInfos result = authService.createAuthinfos(employee);
        Assertions.assertEquals("djohn", result.getLogin());
        Assertions.assertEquals("djohn", result.getPassword());
    }

    @Test
    public void testCheckCredentialsWithValidAuthInfos() throws Exception {
        when(authRepository.findByLogin("djohn")).thenReturn(Optional.of(authInfos));
        Employee result = authService.checkCredentials(authInfos);
        Assertions.assertEquals(employee, result);
    }

    @Test
    public void testCheckCredentialsWithInvalidLogin() {
        when(authRepository.findByLogin("jdoe")).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class, () -> authService.checkCredentials(authInfos));
    }

    @Test
    public void testCheckCredentialsWithInvalidPassword() {
        AuthInfos authInfosToTest = new AuthInfos();
        authInfosToTest.setLogin("jdoe");
        authInfosToTest.setPassword("wrong_password");
        when(authRepository.findByLogin("jdoe")).thenReturn(Optional.of(authInfos));
        Assertions.assertThrows(Exception.class, () -> authService.checkCredentials(authInfosToTest));
    }
}