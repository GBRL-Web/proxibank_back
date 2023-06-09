package com.projet.proxibanque_groupe3.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ClientServiceTest {


    @Autowired
    private ClientService clientService;

    @Test
    void should_return_an_empty_optionnal() {
        assertThat(clientService.getClientByCounselorId(15).isEmpty());
    }

    @Test
    void should_return_a_non_empty_optionnal(){
        assertThat(clientService.getClientByCounselorId(1).isPresent());
    }
}