package com.ayed.gestionstock.controller;

import com.ayed.gestionstock.controller.api.MvtStkApi;
import com.ayed.gestionstock.dto.MvtStkDTO;
import com.ayed.gestionstock.service.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStkController implements MvtStkApi {

  private MvtStkService mvtStkService;

  @Autowired
  public MvtStkController(MvtStkService service) {
    this.mvtStkService =mvtStkService;
  }

  @Override
  public BigDecimal stockReelArticle(Integer idArticle) {
    return mvtStkService.stockReelArticle(idArticle);
  }

  @Override
  public List<MvtStkDTO> mvtStkArticle(Integer idArticle) {

    return mvtStkService.mvtStkArticle(idArticle);
  }

  @Override
  public MvtStkDTO entreeStock(MvtStkDTO mvtStkDTO) {

    return mvtStkService.entreeStock(mvtStkDTO);
  }

  @Override
  public MvtStkDTO sortieStock(MvtStkDTO mvtStkDTO) {

    return mvtStkService.sortieStock(mvtStkDTO);
  }

  @Override
  public MvtStkDTO correctionStockPos(MvtStkDTO mvtStkDTO) {

    return mvtStkService.correctionStockPos(mvtStkDTO);
  }

  @Override
  public MvtStkDTO correctionStockNeg(MvtStkDTO mvtStkDTO) {

    return mvtStkService.correctionStockNeg(mvtStkDTO);
  }
}
