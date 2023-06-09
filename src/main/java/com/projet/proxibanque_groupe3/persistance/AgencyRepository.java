package com.projet.proxibanque_groupe3.persistance;

import com.projet.proxibanque_groupe3.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency, Long> {
}
