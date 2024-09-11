package com.ayed.gestionstock.controller;


import com.ayed.gestionstock.controller.api.VentesApi;
import com.ayed.gestionstock.dto.VentesDTO;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.service.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {

  private VentesService ventesService;
  private mappersDTO mappersDTo;
  @Autowired
  public VentesController(VentesService ventesService, mappersDTO mappersDTo) {
    this.ventesService = ventesService;
      this.mappersDTo = mappersDTo;
  }

  @Override
  public VentesDTO save(VentesDTO ventesDTO) {
    return ventesService.save(ventesDTO);
  }

  @Override
  public VentesDTO findById(Integer id) {
    return ventesService.findById(id);
  }

  @Override
  public VentesDTO findByCode(String code) {
    return ventesService.findByCode(code);
  }

  @Override
  public List<VentesDTO> findAll() {
    return ventesService.findAll();
  }

  @Override
  public void delete(Integer id) {
    ventesService.delete(id);
  }
}
