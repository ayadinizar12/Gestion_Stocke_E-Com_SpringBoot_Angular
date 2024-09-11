package com.ayed.gestionstock.service;

import com.ayed.gestionstock.dto.VentesDTO;
import java.util.List;

public interface VentesService {

  VentesDTO save(VentesDTO ventesDTO);

  VentesDTO findById(Integer id);

  VentesDTO findByCode(String code);

  List<VentesDTO> findAll();

  void delete(Integer id);

}
