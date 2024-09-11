package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.CommandeClientDTO;
import com.ayed.gestionstock.dto.LigneCommandeClientDTO;
import com.ayed.gestionstock.dto.MvtStkDTO;
import com.ayed.gestionstock.entity.*;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.ArticleRepo;
import com.ayed.gestionstock.repository.ClientRepo;
import com.ayed.gestionstock.repository.CommandeClientRepo;
import com.ayed.gestionstock.repository.LigneCommandeClientRepo;
import com.ayed.gestionstock.service.CommandeClientService;
import com.ayed.gestionstock.service.MvtStkService;
import com.ayed.gestionstock.validator.articleValidator;
import com.ayed.gestionstock.validator.commandeClientValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

  private LigneCommandeClientRepo ligneCommandeClientRepository;
  private CommandeClientRepo commandeClientRepository;
  private ClientRepo clientRepository;
  private ArticleRepo articleRepository;
  private mappersDTO mappersDTo;
  private MvtStkService mvtStkService;

    public CommandeClientServiceImpl(LigneCommandeClientRepo ligneCommandeClientRepository, CommandeClientRepo commandeClientRepository,
                                     ClientRepo clientRepository,
                                     ArticleRepo articleRepository,
                                     mappersDTO mappersDTo, MvtStkService mvtStkService) {
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.mappersDTo = mappersDTo;
        this.mvtStkService = mvtStkService;
    }


    @Override
  public CommandeClientDTO save(CommandeClientDTO commandeClientDTO) {

    List<String> errors = commandeClientValidator.validate(commandeClientDTO);

    if (!errors.isEmpty()) {
      log.error("Commande client n'est pas valide");
      throw new invalidEntityException("La commande client n'est pas valide",
              errorCodes.CMD_CLT_NOT_VALID,errors);
    }

    if (commandeClientDTO.getId() != null && commandeClientDTO.isCommandeLivree()) {
      throw new invalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
              errorCodes.CMD_CLT_NON_MODIFIABLE);
    }

      Optional<Client> client = clientRepository.findById(commandeClientDTO.getClient().getId());
    if (client.isEmpty()) {
      log.warn("Client with ID {} was not found in the DB", commandeClientDTO.getClient().getId());
      throw new entityNotFoundException("Aucun client avec l'ID" + commandeClientDTO.getClient()
              .getId() + " n'a ete trouve dans la BDD",
          errorCodes.CLT_NOT_FOUND);
    }


    List<String> articleErrors = new ArrayList<>();
    if (commandeClientDTO.getLigneCommandeClients() != null) {
      commandeClientDTO.getLigneCommandeClients()
              .forEach(ligCmdClt -> {
                if (ligCmdClt.getArticle() != null) {
                  Optional<Article> article = articleRepository
                          .findById(ligCmdClt.getArticle().getId());
                  if (article.isEmpty()) {
                    articleErrors.add("L'article avec l'ID "
                                    + ligCmdClt.getArticle().getId()
                                    + " n'existe pas");
                  }
                } else {
                  articleErrors.add("Impossible d'enregister une commande avec un aticle "
                          +ligCmdClt.getArticle().getId()+" ");
                }
              });
    }

    if (!articleErrors.isEmpty()) {
      log.warn("");
      throw new invalidEntityException("Article n'existe pas dans la BDD",
              errorCodes.ART_NOT_FOUND,articleErrors);
    }

    commandeClientDTO.setDateCommande(Instant.now());

    CommandeClient savedCmdClt = commandeClientRepository.save(
            mappersDTo.commandeClienttoEntity(commandeClientDTO));

    if (commandeClientDTO.getLigneCommandeClients() != null) {
      commandeClientDTO.getLigneCommandeClients()
              .forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = mappersDTo.
                        ligneCommandeClienttoEntity(ligCmdClt);

                ligneCommandeClient.setCommandeClient(savedCmdClt);

                ligneCommandeClient.setIdEntreprise(commandeClientDTO.getIdEntreprise());

                LigneCommandeClient savedLigneCmd = ligneCommandeClientRepository
                        .save(ligneCommandeClient);

                effectuerSortie(savedLigneCmd);
              });
    }

    return mappersDTo.commandeClienttoDTO(savedCmdClt);
  }

  @Override
  public CommandeClientDTO findById(Integer id) {
    if (id == null) {
      log.error("Commande client ID is NULL");
      return null;
    }

    return commandeClientRepository.findById(id)
        .map(mappersDTo::commandeClienttoDTO)
        .orElseThrow(() ->
                new entityNotFoundException(
                        "Aucune commande client n'a ete trouve avec l'ID "
                                + id, errorCodes.CMD_CLT_NOT_FOUND
                )
        );
  }

  @Override
  public CommandeClientDTO findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Commande client CODE is NULL");
      return null;
    }

    return commandeClientRepository.findCommandeClientByCode(code)
        .map(mappersDTo::commandeClienttoDTO)
            .orElseThrow(() ->
                    new entityNotFoundException(
                            "Aucune commande client n'a ete trouve avec le CODE "
                                    + code, errorCodes.CMD_CLT_NOT_FOUND
                    )
            );
  }

  @Override
  public List<CommandeClientDTO> findAll() {
    return commandeClientRepository.findAll()
            .stream()
            .map(mappersDTo::commandeClienttoDTO)
            .collect(Collectors.toList());
    }



  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Commande client ID is NULL");
      return;
    }

    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(id);
    if (!ligneCommandeClients.isEmpty()) {
      throw new invalidOperationException("Impossible de supprimer une commande client deja utilisee",
          errorCodes.CMD_CLT_ALREADY_IN_USE);
    }
    commandeClientRepository.deleteById(id);
  }

  @Override
  public List<LigneCommandeClientDTO> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
    return ligneCommandeClientRepository
            .findAllByCommandeClientId(idCommande)
            .stream()
            .map(mappersDTo::ligneCommandeClienttoDTO)
            .collect(Collectors.toList());
  }

  @Override
  public CommandeClientDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    checkIdCommande(idCommande);
    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
      log.error("L'etat de la commande client is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
          errorCodes.CMD_CLT_NON_MODIFIABLE);
    }
    CommandeClientDTO commandeClientDTO = checkEtatCommande(idCommande);
    commandeClientDTO.setEtatCommande(etatCommande);

    CommandeClient savedCmdClt = commandeClientRepository.save(
            mappersDTo.commandeClienttoEntity(commandeClientDTO));

    if (commandeClientDTO.isCommandeLivree()) {
      updateMvtStk(idCommande);
    }

    return mappersDTo.commandeClienttoDTO(savedCmdClt);
  }

  @Override
  public CommandeClientDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
      log.error("L'ID de la ligne commande is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
          errorCodes.CMD_CLT_NON_MODIFIABLE);
    }

    CommandeClientDTO commandeClientDTO = checkEtatCommande(idCommande);
    Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);

    LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
    ligneCommandeClient.setQuantite(quantite);
    ligneCommandeClientRepository.save(ligneCommandeClient);

    return commandeClientDTO;
  }

  @Override
  public CommandeClientDTO updateClient(Integer idCommande, Integer idClient) {
    checkIdCommande(idCommande);
    if (idClient == null) {
      log.error("L'ID du client is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",
          errorCodes.CMD_CLT_NON_MODIFIABLE);
    }
    CommandeClientDTO commandeClientDTO = checkEtatCommande(idCommande);
    Optional<Client> clientOptional = clientRepository.findById(idClient);
    if (clientOptional.isEmpty()) {
      throw new entityNotFoundException(
          "Aucun client n'a ete trouve avec l'ID " + idClient, errorCodes.CLT_NOT_FOUND);
    }
    commandeClientDTO.setClient(mappersDTo.clienttoDTO(clientOptional.get()));

    return mappersDTo.commandeClienttoDTO(commandeClientRepository
            .save(mappersDTo.commandeClienttoEntity(commandeClientDTO)));
  }

  @Override
  public CommandeClientDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);
    checkIdArticle(idArticle, "nouvel");

    CommandeClientDTO commandeClientDTO = checkEtatCommande(idCommande);

    Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

    Optional<Article> articleOptional = articleRepository.findById(idArticle);
    if (articleOptional.isEmpty()) {
      throw new entityNotFoundException(
          "Aucune article n'a ete trouve avec l'ID " + idArticle, errorCodes.ART_NOT_FOUND);
    }

    List<String> errors = articleValidator.validate(mappersDTo.articletoDTO(articleOptional.get()));
    if (!errors.isEmpty()) {
      throw new invalidEntityException("Article invalid", errorCodes.ART_NOT_VALID, errors);
    }

    LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();
    ligneCommandeClientToSaved.setArticle(articleOptional.get());
    ligneCommandeClientRepository.save(ligneCommandeClientToSaved);

    return commandeClientDTO;
  }

  @Override
  public CommandeClientDTO deleteArticle(Integer idCommande, Integer idLigneCommande) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    CommandeClientDTO commandeClientDTO = checkEtatCommande(idCommande);
    // Just to check the LigneCommandeClient and inform the client in case it is absent
    findLigneCommandeClient(idLigneCommande);
    ligneCommandeClientRepository.deleteById(idLigneCommande);

    return commandeClientDTO;
  }

  private CommandeClientDTO checkEtatCommande(Integer idCommande) {
    CommandeClientDTO commandeClientDTO = findById(idCommande);
    if (commandeClientDTO.isCommandeLivree()) {
      throw new invalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
              errorCodes.CMD_CLT_NON_MODIFIABLE);
    }
    return commandeClientDTO;
  }

  private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {
    Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository
            .findById(idLigneCommande);

    if (ligneCommandeClientOptional.isEmpty()) {
      throw new entityNotFoundException(
          "Aucune ligne commande client n'a ete trouve avec l'ID "
                  + idLigneCommande, errorCodes.CMD_CLT_NOT_FOUND);
    }
    return ligneCommandeClientOptional;
  }

  private void checkIdCommande(Integer idCommande) {
    if (idCommande == null) {
      log.error("Commande client ID is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
          errorCodes.CMD_CLT_NON_MODIFIABLE);
    }
  }

  private void checkIdLigneCommande(Integer idLigneCommande) {
    if (idLigneCommande == null) {
      log.error("L'ID de la ligne commande is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
          errorCodes.CMD_CLT_NON_MODIFIABLE);
    }
  }

  private void checkIdArticle(Integer idArticle, String msg) {
    if (idArticle == null) {
      log.error("L'ID de " + msg + " is NULL");
      throw new invalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg + " ID article null",
          errorCodes.CMD_CLT_NON_MODIFIABLE);
    }
  }

  private void updateMvtStk(Integer idCommande) {
    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository
            .findAllByCommandeClientId(idCommande);

    ligneCommandeClients.forEach(lig -> {
      effectuerSortie(lig);
    });
  }

  private void effectuerSortie(LigneCommandeClient lig) {
    MvtStkDTO mvtStkDTO = MvtStkDTO.builder()
            .article(mappersDTo.articletoDTO(lig.getArticle()))
            .dateMvt(Instant.now())
            .typeMvt(TypeMvtStk.SORTIE)
            .sourceMvt(SourceMvtStk.COMMANDE_CLIENT)
            .quantite(lig.getQuantite())
            .idEntreprise(lig.getIdEntreprise())
            .build();
    mvtStkService.sortieStock(mvtStkDTO);
  }
}
