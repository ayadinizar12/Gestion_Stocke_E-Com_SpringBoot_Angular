package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.CategoryDTO;
import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.entity.Category;
import com.ayed.gestionstock.entity.Client;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.CategoryRepo;
import com.ayed.gestionstock.repository.ClientRepo;
import com.ayed.gestionstock.service.CategoryService;
import com.ayed.gestionstock.service.ClientService;
import com.ayed.gestionstock.validator.categoryValidator;
import com.ayed.gestionstock.validator.clientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepo clientRepo;
    private mappersDTO mappersDTo;

    public ClientServiceImpl(ClientRepo clientRepo, mappersDTO mappersDTo) {
        this.clientRepo = clientRepo;
        this.mappersDTo = mappersDTo;
    }


    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        List<String> errors = clientValidator.validate(clientDTO);
        if(!errors.isEmpty()) {
            log.error("Client n'est pas Valide {}", clientDTO);
            throw new invalidEntityException("Client n'est pas Valide ", errorCodes.CLT_NOT_VALID);
        }
        return mappersDTo.clienttoDTO(
                clientRepo.save(
                mappersDTo.clienttoEntity(clientDTO))
        );

    }

    @Override
    public ClientDTO findById(Integer id) {
        if (id==null){
            log.error("Client id is null");
            return null;
        }

        Optional<Client> client=clientRepo.findById(id);
        return Optional.of(mappersDTo.clienttoDTO(client.get())).orElseThrow(()->
                new entityNotFoundException("Aucun client avec l'id = "+id+" dans BD",
                        errorCodes.CLT_NOT_FOUND));
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepo.findAll().stream()
                .map(mappersDTo::clienttoDTO)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Client id is null");
            return;
        }
        clientRepo.deleteById(id);
    }
}
