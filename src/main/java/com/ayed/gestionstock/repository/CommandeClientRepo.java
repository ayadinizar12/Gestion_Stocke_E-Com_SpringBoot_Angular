package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepo extends JpaRepository<CommandeClient,Integer> {
    Optional<CommandeClient> findCommandeClientByCode(String code);

    List<CommandeClient> findAllByClientId(Integer id);
}
