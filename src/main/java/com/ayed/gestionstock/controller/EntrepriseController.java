package com.ayed.gestionstock.controller;

import com.ayed.gestionstock.controller.api.ClientApi;
import com.ayed.gestionstock.controller.api.EntrepriseApi;
import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.dto.EntrepriseDTO;
import com.ayed.gestionstock.service.ClientService;
import com.ayed.gestionstock.service.EntrepriseService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {


    private EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDTO save(EntrepriseDTO entrepriseDTO) {
        return entrepriseService.save(entrepriseDTO);
    }

    @Override
    public EntrepriseDTO findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDTO> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}