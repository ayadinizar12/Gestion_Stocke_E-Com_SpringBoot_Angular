package com.ayed.gestionstock.controller;

import com.ayed.gestionstock.controller.api.CommandeClientApi;
import com.ayed.gestionstock.dto.CommandeClientDTO;
import com.ayed.gestionstock.dto.LigneCommandeClientDTO;
import com.ayed.gestionstock.entity.EtatCommande;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

  private CommandeClientService commandeClientService;
  private mappersDTO mappersDTo;

  @Autowired
  public CommandeClientController(CommandeClientService commandeClientService, mappersDTO mappersDTo) {
    this.commandeClientService = commandeClientService;
      this.mappersDTo = mappersDTo;
  }

  @Override
  public ResponseEntity<CommandeClientDTO> save(CommandeClientDTO commandeClientDTO) {
    return ResponseEntity.ok(commandeClientService.save(commandeClientDTO));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateClient(Integer idCommande, Integer idClient) {
    return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
    return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> deleteArticle(Integer idCommande, Integer idLigneCommande) {
    return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> findById(Integer id) {
    return ResponseEntity.ok(commandeClientService.findById(id));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> findByCode(String code) {
    return ResponseEntity.ok(commandeClientService.findByCode(code));
  }

  @Override
  public ResponseEntity<List<CommandeClientDTO>> findAll() {
    return ResponseEntity.ok(commandeClientService.findAll());
  }

  @Override
  public ResponseEntity<List<LigneCommandeClientDTO>> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
    return ResponseEntity.ok(commandeClientService.findAllLignesCommandesClientByCommandeClientId(idCommande));
  }

  @Override
  public ResponseEntity<Void> delete(Integer id) {
    commandeClientService.delete(id);
    return ResponseEntity.ok().build();
  }
}
