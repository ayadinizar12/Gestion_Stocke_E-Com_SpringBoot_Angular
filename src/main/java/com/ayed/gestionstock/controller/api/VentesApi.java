package com.ayed.gestionstock.controller.api;


import com.ayed.gestionstock.dto.VentesDTO;
import com.ayed.gestionstock.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ayed.gestionstock.utils.Constants.VENTES_ENDPOINT;


@Api("ventes")
public interface VentesApi {

  @PostMapping(VENTES_ENDPOINT + "/create")
  VentesDTO save(@RequestBody VentesDTO ventesDTO);

  @GetMapping(VENTES_ENDPOINT + "/{idVente}")
  VentesDTO findById(@PathVariable("idVente") Integer id);

  @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
  VentesDTO findByCode(@PathVariable("codeVente") String code);

  @GetMapping(VENTES_ENDPOINT + "/all")
  List<VentesDTO> findAll();

  @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
  void delete(@PathVariable("idVente") Integer id);

}
