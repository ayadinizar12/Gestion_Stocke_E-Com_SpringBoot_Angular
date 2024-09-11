package com.ayed.gestionstock.controller.api;

import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.dto.EntrepriseDTO;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ayed.gestionstock.utils.Constants.APP_ROOT;
import static com.ayed.gestionstock.utils.Constants.ENTREPRISE_ENDPOINT;
@Api("entreprise")
public interface EntrepriseApi {

    @PostMapping(ENTREPRISE_ENDPOINT + "/create")
    EntrepriseDTO save(@RequestBody EntrepriseDTO entrepriseDTO);

    @GetMapping(ENTREPRISE_ENDPOINT + "/{idEntreprise}")
    EntrepriseDTO findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(ENTREPRISE_ENDPOINT + "/all")
    List<EntrepriseDTO> findAll();

    @DeleteMapping(ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);
}
