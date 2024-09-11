package com.ayed.gestionstock.service;

import com.ayed.gestionstock.dto.CommandeClientDTO;
import com.ayed.gestionstock.dto.LigneCommandeClientDTO;
import com.ayed.gestionstock.entity.EtatCommande;


import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

  CommandeClientDTO save(CommandeClientDTO commandeClientDTO);

  CommandeClientDTO findById(Integer id);

  CommandeClientDTO findByCode(String code);

  List<CommandeClientDTO> findAll();

  void delete(Integer id);

  CommandeClientDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

  CommandeClientDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

  CommandeClientDTO updateClient(Integer idCommande, Integer idClient);

  CommandeClientDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

  CommandeClientDTO deleteArticle(Integer idCommande, Integer idLigneCommande);

  List<LigneCommandeClientDTO> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);
}
