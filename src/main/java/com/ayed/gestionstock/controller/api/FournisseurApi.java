package com.ayed.gestionstock.controller.api;


import com.ayed.gestionstock.dto.FournisseurDTO;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ayed.gestionstock.utils.Constants.FOURNISSEUR_ENDPOINT;


@Api("fournisseur")
public interface FournisseurApi {

  @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
  FournisseurDTO save(@RequestBody FournisseurDTO fournisseurDTO);

  @GetMapping(FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
  FournisseurDTO findById(@PathVariable("idFournisseur") Integer id);

  @GetMapping(FOURNISSEUR_ENDPOINT + "/all")
  List<FournisseurDTO> findAll();

  @DeleteMapping(FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
  void delete(@PathVariable("idFournisseur") Integer id);

}
