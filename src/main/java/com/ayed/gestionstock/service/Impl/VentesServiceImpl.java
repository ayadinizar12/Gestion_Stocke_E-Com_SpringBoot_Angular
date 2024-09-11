package com.ayed.gestionstock.service.Impl;


import com.ayed.gestionstock.dto.ArticleDTO;
import com.ayed.gestionstock.dto.LigneVenteDTO;
import com.ayed.gestionstock.dto.MvtStkDTO;
import com.ayed.gestionstock.dto.VentesDTO;
import com.ayed.gestionstock.entity.*;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.ArticleRepo;
import com.ayed.gestionstock.repository.LigneVentesRepo;
import com.ayed.gestionstock.repository.VentesRepo;
import com.ayed.gestionstock.service.MvtStkService;
import com.ayed.gestionstock.service.VentesService;
import com.ayed.gestionstock.validator.ventesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

  private ArticleRepo articleRepository;
  private VentesRepo ventesRepository;
  private LigneVentesRepo ligneVenteRepository;
  private MvtStkService mvtStkService;
  private mappersDTO mappersDTo;

  @Autowired
  public VentesServiceImpl(ArticleRepo articleRepository, VentesRepo ventesRepository,
                           LigneVentesRepo ligneVenteRepository, MvtStkService mvtStkService, mappersDTO mappersDTo) {
    this.articleRepository = articleRepository;
    this.ventesRepository = ventesRepository;
    this.ligneVenteRepository = ligneVenteRepository;
    this.mvtStkService = mvtStkService;
    this.mappersDTo = mappersDTo;
  }

  @Override
  public VentesDTO save(VentesDTO ventesDTO) {
    List<String> errors = ventesValidator.validate(ventesDTO);
    if (!errors.isEmpty()) {
      log.error("Ventes n'est pas valide");
      throw new invalidEntityException("L'objet vente n'est pas valide",
              errorCodes.VNT_NOT_VALID, errors);
    }

    List<String> articleErrors = new ArrayList<>();

    ventesDTO.getLigneVentes().forEach(ligneVenteDto -> {
      Optional<Article> article = articleRepository
              .findById(ligneVenteDto.getArticle().getId());
      if (article.isEmpty()) {
        articleErrors.add("Aucun article avec l'ID "
                + ligneVenteDto.getArticle().getId()
                + " n'a ete trouve dans la BDD");
      }
    });

    if (!articleErrors.isEmpty()) {
      log.error("One or more articles were not found in the DB, {}", errors);
      throw new invalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD",
              errorCodes.VNT_NOT_VALID, errors);
    }

    Ventes savedVentes = ventesRepository.save(mappersDTo.ventestoEntity(ventesDTO));

    ventesDTO.getLigneVentes()
            .forEach(ligneVenteDto -> {
              LigneVente ligneVente = mappersDTo.ligneVentetoEntity(ligneVenteDto);
              ligneVente.setVentes(savedVentes);
              ligneVenteRepository.save(ligneVente);
              updateMvtStk(ligneVente);
            });

    return mappersDTo.ventestoDTO(savedVentes);
  }

  @Override
  public VentesDTO findById(Integer id) {
    if (id == null) {
      log.error("Ventes ID is NULL");
      return null;
    }
    return ventesRepository
            .findById(id)
            .map(mappersDTo::ventestoDTO)
            .orElseThrow(() -> new entityNotFoundException("Aucun vente n'a ete trouve dans la BDD",
                    errorCodes.VNT_NOT_FOUND)
            );
  }

  @Override
  public VentesDTO findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Vente CODE is NULL");
      return null;
    }
    return ventesRepository
            .findVentesByCode(code)
            .map(mappersDTo::ventestoDTO)
            .orElseThrow(() -> new entityNotFoundException(
                    "Aucune vente client n'a ete trouve avec le CODE "
                            + code, errorCodes.VNT_NOT_VALID)
            );
  }

  @Override
  public List<VentesDTO> findAll() {
    return ventesRepository
            .findAll()
            .stream()
            .map(mappersDTo::ventestoDTO)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Vente ID is NULL");
      return;
    }
    List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
    if (!ligneVentes.isEmpty()) {
      throw new invalidOperationException("Impossible de supprimer une vente ...",
              errorCodes.VNT_ALREADY_IN_USE);
    }
    ventesRepository.deleteById(id);
  }

  private void updateMvtStk(LigneVente lig) {
    MvtStkDTO mvtStkDTO = MvtStkDTO.builder()
            .article(mappersDTo.articletoDTO(lig.getArticle()))
            .dateMvt(Instant.now())
            .typeMvt(TypeMvtStk.SORTIE)
            .sourceMvt(SourceMvtStk.VENTE)
            .quantite(lig.getQuantite())
            .idEntreprise(lig.getIdEntreprise())
            .build();
    mvtStkService.sortieStock(mvtStkDTO);
  }
}
