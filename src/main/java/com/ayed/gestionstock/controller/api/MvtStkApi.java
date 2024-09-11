package com.ayed.gestionstock.controller.api;


import com.ayed.gestionstock.dto.MvtStkDTO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.math.BigDecimal;
import java.util.List;
import static com.ayed.gestionstock.utils.Constants.APP_ROOT;


@Api("mvtstk")
public interface MvtStkApi {

  @GetMapping(APP_ROOT + "/mvtstk/stockreel/{idArticle}")
  BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

  @GetMapping(APP_ROOT + "/mvtstk/filter/article/{idArticle}")
  List<MvtStkDTO> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

  @PostMapping(APP_ROOT + "/mvtstk/entree")
  MvtStkDTO entreeStock(@RequestBody MvtStkDTO mvtStkDTO);

  @PostMapping(APP_ROOT + "/mvtstk/sortie")
  MvtStkDTO sortieStock(@RequestBody MvtStkDTO mvtStkDTO);

  @PostMapping(APP_ROOT + "/mvtstk/correctionpos")
  MvtStkDTO correctionStockPos(@RequestBody MvtStkDTO mvtStkDTO);

  @PostMapping(APP_ROOT + "/mvtstk/correctionneg")
  MvtStkDTO correctionStockNeg(@RequestBody MvtStkDTO mvtStkDTO);

}
