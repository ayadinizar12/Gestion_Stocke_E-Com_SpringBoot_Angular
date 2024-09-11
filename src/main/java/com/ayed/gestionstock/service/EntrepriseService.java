package com.ayed.gestionstock.service;

import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.dto.EntrepriseDTO;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDTO save(EntrepriseDTO entrepriseDTO);
    EntrepriseDTO findById(Integer id);

    List<EntrepriseDTO> findAll();

    void delete(Integer id);
}
