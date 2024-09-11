package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.*;
import com.ayed.gestionstock.entity.*;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.ArticleRepo;
import com.ayed.gestionstock.repository.CommandeFournisseurRepo;
import com.ayed.gestionstock.repository.FournisseurRepo;
import com.ayed.gestionstock.repository.LigneCommandeFournisseurRepo;
import com.ayed.gestionstock.service.CommandeFournisseurService;
import com.ayed.gestionstock.service.MvtStkService;
import com.ayed.gestionstock.validator.articleValidator;
import com.ayed.gestionstock.validator.commandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

  private CommandeFournisseurRepo commandeFournisseurRepository;
  private LigneCommandeFournisseurRepo ligneCommandeFournisseurRepository;
  private FournisseurRepo fournisseurRepository;
  private ArticleRepo articleRepository;
  private MvtStkService mvtStkService;
  private mappersDTO mappersDTo;

  @Autowired
  public CommandeFournisseurServiceImpl(CommandeFournisseurRepo commandeFournisseurRepository,
                                        FournisseurRepo fournisseurRepository,
                                        ArticleRepo articleRepository,
                                        LigneCommandeFournisseurRepo ligneCommandeFournisseurRepository,
                                        MvtStkService mvtStkService,
                                        mappersDTO mappersDTo) {
    this.commandeFournisseurRepository = commandeFournisseurRepository;
    this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    this.fournisseurRepository = fournisseurRepository;
    this.articleRepository = articleRepository;
    this.mvtStkService = mvtStkService;
    this.mappersDTo = mappersDTo;
  }

  @Override
  public CommandeFournisseurDTO save(CommandeFournisseurDTO commandeFournisseurDTO) {

    List<String> errors = commandeFournisseurValidator.validate(commandeFournisseurDTO);

    if (!errors.isEmpty()) {
      log.error("Commande fournisseur n'est pas valide");
      throw new invalidEntityException("La commande fournisseur n'est pas valide",
              errorCodes.CMD_FRN_NOT_VALID, errors);
    }

//    if (commandeFournisseurDTO.getId() != null && commandeFournisseurDTO.isCommandeLivree()) {
//      throw new invalidOperationException(
//              "Impossible de modifier la commande lorsqu'elle est livree",
//              errorCodes.CMD_FRN_NON_MODIFIABLE);
//    }

    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(
            commandeFournisseurDTO.getFournisseur().getId());

    if (fournisseur.isEmpty()) {
      log.warn("Fournisseur with ID {} was not found in the DB",
              commandeFournisseurDTO.getFournisseur().getId());
      throw new entityNotFoundException("Aucun fournisseur avec l'ID"
              + commandeFournisseurDTO.getFournisseur().getId()
              + " n'a ete trouve dans la BDD", errorCodes.FRN_NOT_FOUND);
    }

    List<String> articleErrors = new ArrayList<>();

    if (commandeFournisseurDTO.getLigneCommandeFournisseurs() != null) {

      commandeFournisseurDTO.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {

        if (ligCmdFrs.getArticle() != null) {
          Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticle().getId());

          if (article.isEmpty()) {
            articleErrors.add("L'article avec l'ID "
                    +ligCmdFrs.getArticle().getId() + " n'existe pas");
          }
        } else {
          articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
        }
      });
    }

    if (!articleErrors.isEmpty()) {
      log.warn("");
      throw new invalidEntityException("Article n'existe pas dans la BDD",
              errorCodes.ART_NOT_FOUND, articleErrors);
    }

    commandeFournisseurDTO.setDateCommande(Instant.now());

    CommandeFournisseur savedCmdFrs = commandeFournisseurRepository.save(
            mappersDTo.commandeFournisseurtoEntity(commandeFournisseurDTO));

    if (commandeFournisseurDTO.getLigneCommandeFournisseurs() != null) {
      commandeFournisseurDTO.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
        LigneCommandeFournisseur ligneCommandeFournisseur = mappersDTo
                .ligneCommandeFournisseurtoEntity(ligCmdFrs);

        ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
        ligneCommandeFournisseur.setIdEntreprise(savedCmdFrs.getIdEntreprise());
        LigneCommandeFournisseur saveLigne = ligneCommandeFournisseurRepository
                .save(ligneCommandeFournisseur);

        effectuerEntree(saveLigne);
      });
    }

    return mappersDTo.commandeFournisseurtoDTO(savedCmdFrs);
  }

  @Override
  public CommandeFournisseurDTO findById(Integer id) {
    if (id == null) {
      log.error("Commande fournisseur ID is NULL");
      return null;
    }
    return commandeFournisseurRepository.findById(id)
        .map(mappersDTo::commandeFournisseurtoDTO)
            .orElseThrow(() -> new entityNotFoundException(
            "Aucune commande fournisseur n'a ete trouve avec l'ID "
                    + id, errorCodes.CMD_FRN_NOT_FOUND)
            );
  }


  @Override
  public CommandeFournisseurDTO findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Commande fournisseur CODE is NULL");
      return null;
    }
    return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
        .map(mappersDTo::commandeFournisseurtoDTO)
            .orElseThrow(() -> new entityNotFoundException(
            "Aucune commande fournisseur n'a ete trouve avec le CODE "
                    + code, errorCodes.CMD_FRN_NOT_FOUND)
            );
  }

  @Override
  public List<CommandeFournisseurDTO> findAll() {
    return commandeFournisseurRepository.findAll()
            .stream()
            .map(mappersDTo::commandeFournisseurtoDTO)
            .collect(Collectors.toList());
  }

  @Override
  public List<LigneCommandeFournisseurDTO> findAllLignesCommandesFournisseurByCommandeFournisseurId(
          Integer idCommande) {

    return ligneCommandeFournisseurRepository
            .findAllByCommandeFournisseurId(idCommande)
            .stream()
            .map(mappersDTo::ligneCommandeFournisseurtoDTO)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Commande fournisseur ID is NULL");
      return;
    }
    List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository
            .findAllByCommandeFournisseurId(id);

    if (!ligneCommandeFournisseurs.isEmpty()) {
      throw new invalidOperationException("Impossible de supprimer une commande fournisseur deja utilisee",
              errorCodes.CMD_FRN_ALREADY_IN_USE);
    }
    commandeFournisseurRepository.deleteById(id);
  }

  @Override
  public CommandeFournisseurDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    checkIdCommande(idCommande);

    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
      log.error("L'etat de la commande fournisseur is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
          errorCodes.CMD_FRN_NON_MODIFIABLE);
    }
    CommandeFournisseurDTO commandeFournisseur = checkEtatCommande(idCommande);
    commandeFournisseur.setEtatCommande(etatCommande);

    CommandeFournisseur savedCommande = commandeFournisseurRepository.save(
            mappersDTo.commandeFournisseurtoEntity(commandeFournisseur));

    if (commandeFournisseur.isCommandeLivree()) {
      updateMvtStk(idCommande);
    }
    return mappersDTo.commandeFournisseurtoDTO(savedCommande);
  }

  @Override
  public CommandeFournisseurDTO updateQuantiteCommande(Integer idCommande,
                                                       Integer idLigneCommande,
                                                       BigDecimal quantite) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
      log.error("L'ID de la ligne commande is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
          errorCodes.CMD_FRN_NON_MODIFIABLE);
    }

    CommandeFournisseurDTO commandeFournisseur = checkEtatCommande(idCommande);
    Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional
            = findLigneCommandeFournisseur(idLigneCommande);

    LigneCommandeFournisseur ligneCommandeFounisseur = ligneCommandeFournisseurOptional.get();
    ligneCommandeFounisseur.setQuantite(quantite);
    ligneCommandeFournisseurRepository.save(ligneCommandeFounisseur);

    return commandeFournisseur;
  }

  @Override
  public CommandeFournisseurDTO updateFournisseur(Integer idCommande, Integer idFournisseur) {
    checkIdCommande(idCommande);
    if (idFournisseur == null) {
      log.error("L'ID du fournisseur is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un ID fournisseur null",
          errorCodes.CMD_FRN_NON_MODIFIABLE);
    }
    CommandeFournisseurDTO commandeFournisseur = checkEtatCommande(idCommande);

    Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
    if (fournisseurOptional.isEmpty()) {
      throw new entityNotFoundException(
          "Aucun fournisseur n'a ete trouve avec l'ID " + idFournisseur,
              errorCodes.FRN_NOT_FOUND);
    }
    commandeFournisseur.setFournisseur(mappersDTo.fournisseurtoDTO(fournisseurOptional.get()));

    return mappersDTo.commandeFournisseurtoDTO(
            commandeFournisseurRepository.save(
                    mappersDTo.commandeFournisseurtoEntity(commandeFournisseur))
    );
  }

  @Override
  public CommandeFournisseurDTO updateArticle(Integer idCommande, Integer idLigneCommande,
                                              Integer idArticle) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);
    checkIdArticle(idArticle, "nouvel");

    CommandeFournisseurDTO commandeFournisseur = checkEtatCommande(idCommande);

    Optional<LigneCommandeFournisseur> ligneCommandeFournisseur = findLigneCommandeFournisseur(idLigneCommande);

    Optional<Article> articleOptional = articleRepository.findById(idArticle);
    if (articleOptional.isEmpty()) {
      throw new entityNotFoundException(
          "Aucune article n'a ete trouve avec l'ID " + idArticle, errorCodes.ART_NOT_FOUND);
    }

    List<String> errors = articleValidator.validate(mappersDTo.articletoDTO(articleOptional.get()));
    if (!errors.isEmpty()) {
      throw new invalidEntityException("Article invalid", errorCodes.ART_NOT_VALID, errors);
    }

    LigneCommandeFournisseur ligneCommandeFournisseurToSaved = ligneCommandeFournisseur.get();
    ligneCommandeFournisseurToSaved.setArticle(articleOptional.get());
    ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSaved);

    return commandeFournisseur;
  }

  @Override
  public CommandeFournisseurDTO deleteArticle(Integer idCommande, Integer idLigneCommande) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    CommandeFournisseurDTO commandeFournisseur = checkEtatCommande(idCommande);
    // Just to check the LigneCommandeFournisseur and inform the fournisseur in case it is absent
    findLigneCommandeFournisseur(idLigneCommande);
    ligneCommandeFournisseurRepository.deleteById(idLigneCommande);

    return commandeFournisseur;
  }

  private CommandeFournisseurDTO checkEtatCommande(Integer idCommande) {
    CommandeFournisseurDTO commandeFournisseur = findById(idCommande);
    if (commandeFournisseur.isCommandeLivree()) {
      throw new invalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
              errorCodes.CMD_FRN_NON_MODIFIABLE);
    }
    return commandeFournisseur;
  }

  private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(Integer idLigneCommande) {
    Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =
            ligneCommandeFournisseurRepository.findById(idLigneCommande);
    if (ligneCommandeFournisseurOptional.isEmpty()) {
      throw new entityNotFoundException(
          "Aucune ligne commande fournisseur n'a ete trouve avec l'ID "
                  + idLigneCommande, errorCodes.CMD_FRN_NOT_FOUND);
    }
    return ligneCommandeFournisseurOptional;
  }

  private void checkIdCommande(Integer idCommande) {
    if (idCommande == null) {
      log.error("Commande fournisseur ID is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
          errorCodes.CMD_FRN_NON_MODIFIABLE);
    }
  }

  private void checkIdLigneCommande(Integer idLigneCommande) {
    if (idLigneCommande == null) {
      log.error("L'ID de la ligne commande is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
          errorCodes.CMD_FRN_NON_MODIFIABLE);
    }
  }

  private void checkIdArticle(Integer idArticle, String msg) {
    if (idArticle == null) {
      log.error("L'ID de " + msg + " is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg + " ID article null",
          errorCodes.CMD_FRN_NON_MODIFIABLE);
    }
  }

  private void updateMvtStk(Integer idCommande) {
    List<LigneCommandeFournisseur> ligneCommandeFournisseur = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);
    ligneCommandeFournisseur.forEach(lig -> {
      effectuerEntree(lig);
    });
  }

  private void effectuerEntree(LigneCommandeFournisseur lig) {
    MvtStkDTO mvtStkDto = MvtStkDTO.builder()
        .article(mappersDTo.articletoDTO(lig.getArticle()))
            .dateMvt(Instant.now())
            .typeMvt(TypeMvtStk.ENTREE)
            .sourceMvt(SourceMvtStk.COMMANDE_FOURNISSEUR)
            .quantite(lig.getQuantite())
            .idEntreprise(lig.getIdEntreprise())
            .build();
    mvtStkService.entreeStock(mvtStkDto);
  }
}
