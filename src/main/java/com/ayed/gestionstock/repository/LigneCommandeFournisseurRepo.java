package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepo extends JpaRepository<LigneCommandeFournisseur,Integer> {
    List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idCommande);

    List<LigneCommandeFournisseur> findAllByArticleId(Integer idCommande);
}
