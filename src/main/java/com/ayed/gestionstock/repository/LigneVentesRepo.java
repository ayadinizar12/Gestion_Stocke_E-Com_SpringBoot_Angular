package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneVentesRepo extends JpaRepository<LigneVente,Integer> {
    List<LigneVente> findAllByArticleId(Integer idArticle);

    List<LigneVente> findAllByVenteId(Integer id);
}
