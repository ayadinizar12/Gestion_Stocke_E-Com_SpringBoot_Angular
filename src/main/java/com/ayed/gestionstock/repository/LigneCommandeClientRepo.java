package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.entity.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepo extends JpaRepository<LigneCommandeClient,Integer> {
    List<LigneCommandeClient> findAllByCommandeClientId(Integer id);

    List<LigneCommandeClient> findAllByArticleId(Integer id);
}
