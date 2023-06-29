package com.projet.proxibanque_groupe3.persistance;

import com.projet.proxibanque_groupe3.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ClientRepository extends JpaRepository<Client, Long> {

    public Optional<Set<Client>> getByCounselorId(Long id);
}
