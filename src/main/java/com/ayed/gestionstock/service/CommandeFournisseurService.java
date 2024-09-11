package com.ayed.gestionstock.service;

import com.ayed.gestionstock.dto.CommandeFournisseurDTO;
import com.ayed.gestionstock.dto.LigneCommandeFournisseurDTO;
import com.ayed.gestionstock.entity.EtatCommande;


import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

  CommandeFournisseurDTO save(CommandeFournisseurDTO commandeFournisseurDTO);

  CommandeFournisseurDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

  CommandeFournisseurDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

  CommandeFournisseurDTO updateFournisseur(Integer idCommande, Integer idFournisseur);

  CommandeFournisseurDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

  // Delete article ==> delete LigneCommandeFournisseur
  CommandeFournisseurDTO deleteArticle(Integer idCommande, Integer idLigneCommande);

  CommandeFournisseurDTO findById(Integer id);

  CommandeFournisseurDTO findByCode(String code);

  List<CommandeFournisseurDTO> findAll();

  List<LigneCommandeFournisseurDTO> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

  void delete(Integer id);

}
