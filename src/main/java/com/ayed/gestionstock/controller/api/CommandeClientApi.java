package com.ayed.gestionstock.controller.api;


import com.ayed.gestionstock.dto.CommandeClientDTO;
import com.ayed.gestionstock.dto.LigneCommandeClientDTO;
import com.ayed.gestionstock.entity.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.ayed.gestionstock.utils.Constants.APP_ROOT;


@Api("commandesclients")
public interface CommandeClientApi {


  @PostMapping(APP_ROOT + "/commandesclients/create")
  ResponseEntity<CommandeClientDTO> save(@RequestBody CommandeClientDTO commandeClientDTO);

  @PatchMapping(APP_ROOT + "/commandesclients/update/etat/{idCommande}/{etatCommande}")
  ResponseEntity<CommandeClientDTO> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

  @PatchMapping(APP_ROOT + "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  ResponseEntity<CommandeClientDTO> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

  @PatchMapping(APP_ROOT + "/commandesclients/update/client/{idCommande}/{idClient}")
  ResponseEntity<CommandeClientDTO> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);

  @PatchMapping(APP_ROOT + "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  ResponseEntity<CommandeClientDTO> updateArticle(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

  @DeleteMapping(APP_ROOT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
  ResponseEntity<CommandeClientDTO> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

  @GetMapping(APP_ROOT + "/commandesclients/{idCommandeClient}")
  ResponseEntity<CommandeClientDTO> findById(@PathVariable Integer idCommandeClient);

  @GetMapping(APP_ROOT + "/commandesclients/filter/{codeCommandeClient}")
  ResponseEntity<CommandeClientDTO> findByCode(@PathVariable("codeCommandeClient") String code);

  @GetMapping(APP_ROOT + "/commandesclients/all")
  ResponseEntity<List<CommandeClientDTO>> findAll();

  @GetMapping(APP_ROOT + "/commandesclients/lignesCommande/{idCommande}")
  ResponseEntity<List<LigneCommandeClientDTO>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);

  @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
  ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);

}
