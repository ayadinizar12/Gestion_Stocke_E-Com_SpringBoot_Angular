package com.ayed.gestionstock.controller.api;

import com.ayed.gestionstock.dto.CommandeFournisseurDTO;
import com.ayed.gestionstock.dto.LigneCommandeFournisseurDTO;
import com.ayed.gestionstock.entity.EtatCommande;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import static com.ayed.gestionstock.utils.Constants.*;


@Api("commandefournisseur")
public interface CommandeFournisseurApi {

  @PostMapping(CREATE_COMMANDE_FOURNISSEUR_ENDPOINT)
  CommandeFournisseurDTO save(@RequestBody CommandeFournisseurDTO commandeFournisseurDTO);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}")
  CommandeFournisseurDTO updateEtatCommande(@PathVariable("idCommande") Integer idCommande,
                                            @PathVariable("etatCommande") EtatCommande etatCommande);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT
          + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  CommandeFournisseurDTO updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
                                                @PathVariable("idLigneCommande") Integer idLigneCommande,
                                                @PathVariable("quantite") BigDecimal quantite);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT
          + "/update/fournisseur/{idCommande}/{idFournisseur}")
  CommandeFournisseurDTO updateFournisseur(@PathVariable("idCommande") Integer idCommande,
                                           @PathVariable("idFournisseur") Integer idFournisseur);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT
          + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  CommandeFournisseurDTO updateArticle(@PathVariable("idCommande") Integer idCommande,
                                       @PathVariable("idLigneCommande") Integer idLigneCommande,
                                       @PathVariable("idArticle") Integer idArticle);

  @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT
          + "/delete/article/{idCommande}/{idLigneCommande}")
  CommandeFournisseurDTO deleteArticle(@PathVariable("idCommande") Integer idCommande,
                                       @PathVariable("idLigneCommande") Integer idLigneCommande);

  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT)
  CommandeFournisseurDTO findById(@PathVariable("idCommandeFournisseur") Integer id);

  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT)
  CommandeFournisseurDTO findByCode(@PathVariable("codeCommandeFournisseur") String code);

  @GetMapping(FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT)
  List<CommandeFournisseurDTO> findAll();

  @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}")
  List<LigneCommandeFournisseurDTO> findAllLignesCommandesFournisseurByCommandeFournisseurId(
          @PathVariable("idCommande") Integer idCommande);

  @DeleteMapping(DELETE_COMMANDE_FOURNISSEUR_ENDPOINT)
  void delete(@PathVariable("idCommandeFournisseur") Integer id);

}
