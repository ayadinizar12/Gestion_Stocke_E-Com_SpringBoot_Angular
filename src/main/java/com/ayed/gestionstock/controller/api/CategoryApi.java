package com.ayed.gestionstock.controller.api;

import com.ayed.gestionstock.dto.CategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ayed.gestionstock.utils.Constants.APP_ROOT;
@Api("/categorys/")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT+"/category/create",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie",
            notes = "Cette methode permet d'enregistrer ou modifier une categorie",
            response = CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet category cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet category n'est pas valide")
    })
    CategoryDTO save(@RequestBody CategoryDTO categoryDTO);

    @GetMapping(value = APP_ROOT+"/category/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par ID",
            notes = "Cette methode permet de chercher une categorie par son ID",
            response = CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDTO findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT+"/category/{codeCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par CODE", notes = "Cette methode permet de chercher une categorie par son CODE", response =
            CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    CategoryDTO findByCode(@PathVariable("codeCategory")String codeCategory);

    @GetMapping(value = APP_ROOT+"/categorys",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent "
            + "dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Category / Une liste vide")
    })
    List<CategoryDTO> findAll();

    @DeleteMapping(value = APP_ROOT+"/category/delete/{idCategory}")
    @ApiOperation(value = "Supprimer une category", notes = "Cette methode permet de supprimer une categorie par ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete supprime")
    })
    void delete(@PathVariable("idCategory") Integer id);
}
