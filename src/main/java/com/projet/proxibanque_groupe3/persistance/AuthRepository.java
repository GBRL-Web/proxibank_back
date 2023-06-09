package com.projet.proxibanque_groupe3.persistance;

import com.projet.proxibanque_groupe3.model.AuthInfos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthInfos, Long> {

    Optional<AuthInfos> findByLogin(String login);
}
