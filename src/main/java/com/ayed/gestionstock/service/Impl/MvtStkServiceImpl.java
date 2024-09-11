package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.MvtStkDTO;
import com.ayed.gestionstock.entity.TypeMvtStk;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.MvtStkRepo;
import com.ayed.gestionstock.service.ArticleService;
import com.ayed.gestionstock.service.MvtStkService;
import com.ayed.gestionstock.validator.mvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

  private MvtStkRepo repository;
  private mappersDTO mappersDTo;
  private ArticleService articleService;

  @Autowired
  public MvtStkServiceImpl(MvtStkRepo repository, mappersDTO mappersDTo, ArticleService articleService) {
    this.repository = repository;
    this.mappersDTo = mappersDTo;
      this.articleService = articleService;
  }

  @Override
  public BigDecimal stockReelArticle(Integer idArticle) {
    if (idArticle == null) {
      log.warn("ID article is NULL");
      return BigDecimal.valueOf(-1);
    }
    articleService.findById(idArticle);
    return repository.stockReelArticle(idArticle);
  }

  @Override
  public List<MvtStkDTO> mvtStkArticle(Integer idArticle) {
    return repository
            .findAllByArticleId(idArticle)
            .stream()
            .map(mappersDTo::mvtStktoDTO)
            .collect(Collectors.toList());
  }

  @Override
  public MvtStkDTO entreeStock(MvtStkDTO mvtStkDTO) {

    return entreePositive(mvtStkDTO, TypeMvtStk.ENTREE);
  }

  @Override
  public MvtStkDTO sortieStock(MvtStkDTO mvtStkDTO) {

    return sortieNegative(mvtStkDTO, TypeMvtStk.SORTIE);
  }

  @Override
  public MvtStkDTO correctionStockPos(MvtStkDTO mvtStkDTO) {

    return entreePositive(mvtStkDTO, TypeMvtStk.CORRECTION_POS);
  }

  @Override
  public MvtStkDTO correctionStockNeg(MvtStkDTO dto) {

    return sortieNegative(dto, TypeMvtStk.CORRECTION_NEG);
  }

  private MvtStkDTO entreePositive(MvtStkDTO dto, TypeMvtStk typeMvtStk) {
    List<String> errors = mvtStkValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new invalidEntityException("Le mouvement du stock n'est pas valide",
              errorCodes.MVT_STK_NOT_VALID, errors);
    }

    dto.setQuantite(BigDecimal
            .valueOf(Math.abs(
                    dto.getQuantite()
                            .doubleValue())
            )
    );

    dto.setTypeMvt(typeMvtStk);

    return mappersDTo.mvtStktoDTO(repository.save(mappersDTo.mvtStktoEntity(dto)));
  }

  private MvtStkDTO sortieNegative(MvtStkDTO dto, TypeMvtStk typeMvtStk) {

    List<String> errors = mvtStkValidator.validate(dto);

    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new invalidEntityException("Le mouvement du stock n'est pas valide",
              errorCodes.MVT_STK_NOT_VALID, errors);
    }

    dto.setQuantite(BigDecimal
            .valueOf(Math.abs(
                    dto.getQuantite()
                            .doubleValue()) * -1
            )
    );

    dto.setTypeMvt(typeMvtStk);

    return mappersDTo.mvtStktoDTO(repository.save(mappersDTo.mvtStktoEntity(dto)));
  }
}