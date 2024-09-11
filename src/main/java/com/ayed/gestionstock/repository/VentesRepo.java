package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentesRepo extends JpaRepository<Ventes,Integer> {
    Optional<Ventes> findVentesByCode(String code);
}
