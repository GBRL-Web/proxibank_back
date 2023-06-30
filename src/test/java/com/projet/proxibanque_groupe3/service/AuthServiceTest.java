package com.projet.proxibanque_groupe3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.projet.proxibanque_groupe3.exceptions.NotFoundException;
import com.projet.proxibanque_groupe3.model.AuthInfos;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.persistance.AuthRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private AuthRepository authRepository;
    @Mock
    private AuthInfos authInfos;
    @Mock
    private Employee employee;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService();
        authService.authRepository = authRepository;
    }

    @Test
    void testCreateAuthinfos() {
        // Prepare test data
        String name = "John";
        String surname = "Doe";
        when(employee.getName()).thenReturn(name);
        when(employee.getSurname()).thenReturn(surname);

        // Call the method
        AuthInfos authInfos = authService.createAuthinfos(employee);

        // Assertions
        assertNotNull(authInfos);
        assertEquals(Character.toLowerCase(surname.charAt(0)) + name.toLowerCase(), authInfos.getLogin());
        assertEquals(Character.toLowerCase(surname.charAt(0)) + name.toLowerCase(), authInfos.getPassword());
    }

    @Test
    void testCheckCredentials_WhenAuthInfoExistsAndPasswordsMatch() throws NotFoundException {
        // Prepare test data
        String login = "johndoe";
        String password = "password";
        when(authInfos.getLogin()).thenReturn(login);
        when(authInfos.getPassword()).thenReturn(password);
        when(authInfos.getEmployee()).thenReturn(employee);
        when(authRepository.findByLogin(login)).thenReturn(Optional.of(authInfos));

        // Call the method
        Employee result = authService.checkCredentials(authInfos);

        // Assertions
        assertNotNull(result);
        assertEquals(employee, result);
        verify(authRepository, times(1)).findByLogin(login);
        verify(authInfos, times(1)).getPassword();
        verify(authInfos, never()).getEmployee().getName();
        verify(authInfos, never()).getEmployee().getSurname();
    }

    @Test
    void testCheckCredentials_WhenAuthInfoDoesNotExist() {
        // Prepare test data
        String login = "unknownlogin";
        when(authInfos.getLogin()).thenReturn(login);
        when(authRepository.findByLogin(login)).thenReturn(Optional.empty());

        // Call the method and assert exception
        assertThrows(NotFoundException.class, () -> authService.checkCredentials(authInfos));

        // Verify that error message is logged
        verify(authService.logger, times(1)).error("Failed login attempt: unknown login {}.", login);
    }

    @Test
    void testCheckCredentials_WhenPasswordsDoNotMatch() {
        // Prepare test data
        String login = "johndoe";
        String password = "password";
        when(authInfos.getLogin()).thenReturn(login);
        when(authInfos.getPassword()).thenReturn(password);
        when(authRepository.findByLogin(login)).thenReturn(Optional.of(authInfos));

        // Call the method and assert exception
        assertThrows(NotFoundException.class, () -> authService.checkCredentials(authInfos));

        // Verify that error message is logged
        verify(authService.logger, times(1)).error("Failed login attempt: incorrect password for account {}.", login);
    }
}
