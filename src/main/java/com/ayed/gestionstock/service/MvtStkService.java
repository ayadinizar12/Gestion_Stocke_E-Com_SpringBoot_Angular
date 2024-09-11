package com.ayed.gestionstock.service;

import com.ayed.gestionstock.dto.MvtStkDTO;
import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {

  BigDecimal stockReelArticle(Integer idArticle);

  List<MvtStkDTO> mvtStkArticle(Integer idArticle);


  MvtStkDTO entreeStock(MvtStkDTO mvtStkDto);

  MvtStkDTO sortieStock(MvtStkDTO mvtStkDTO);

  MvtStkDTO correctionStockPos(MvtStkDTO mvtStkDTO);

  MvtStkDTO correctionStockNeg(MvtStkDTO mvtStkDTO);

}
