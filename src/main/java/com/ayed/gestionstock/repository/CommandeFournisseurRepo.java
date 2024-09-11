package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.CommandeClient;
import com.ayed.gestionstock.entity.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepo extends JpaRepository<CommandeFournisseur,Integer> {

    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

    List<CommandeClient> findAllByFournisseurId(Integer id);
}
