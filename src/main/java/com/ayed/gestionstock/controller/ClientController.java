package com.ayed.gestionstock.controller;

import com.ayed.gestionstock.controller.api.CategoryApi;
import com.ayed.gestionstock.controller.api.ClientApi;
import com.ayed.gestionstock.dto.CategoryDTO;
import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.service.CategoryService;
import com.ayed.gestionstock.service.ClientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        return clientService.save(clientDTO) ;
    }

    @Override
    public ClientDTO findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientService.findAll() ;
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}