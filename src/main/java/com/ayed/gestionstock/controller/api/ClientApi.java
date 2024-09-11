package com.ayed.gestionstock.controller.api;

import com.ayed.gestionstock.dto.CategoryDTO;
import com.ayed.gestionstock.dto.ClientDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ayed.gestionstock.utils.Constants.APP_ROOT;

@Api("client")
public interface ClientApi {

    @PostMapping(value = APP_ROOT+"/client/create",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client",
            notes = "Cette methode permet d'enregistrer ou modifier un client",
            response = ClientDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")
    })
    ClientDTO save(@RequestBody ClientDTO clientDTO);

    @GetMapping(value = APP_ROOT+"/client/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un clinet par ID",
            notes = "Cette methode permet de chercher un client par son ID",
            response = ClientDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le clinet a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune client n'existe dans la BDD avec l'ID fourni")
    })
    ClientDTO findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT+"/clients",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des client", notes = "Cette methode permet de chercher et renvoyer la liste des client qui existent "
            + "dans la BDD", responseContainer = "List<ClientDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des client / Une liste vide")
    })
    List<ClientDTO> findAll();

    @DeleteMapping(value = APP_ROOT+"/client/delete/{idClient}")
    @ApiOperation(value = "Supprimer un client", notes = "Cette methode permet de supprimer un client par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete supprime")
    })
    void delete(@PathVariable("idClient") Integer id);
}
