package com.ayed.gestionstock.controller.api;


import com.ayed.gestionstock.dto.ChangerMotDePasseUtilisateurDTO;
import com.ayed.gestionstock.dto.UtilisateurDTO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.ayed.gestionstock.utils.Constants.UTILISATEUR_ENDPOINT;


@Api("utilisateurs")
public interface UtilisateurApi {

  @PostMapping(UTILISATEUR_ENDPOINT + "/create")
  UtilisateurDTO save(@RequestBody UtilisateurDTO utilisateurDTO);

  @PostMapping(UTILISATEUR_ENDPOINT + "/update/password")
  UtilisateurDTO changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDTO dto);

  @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
  UtilisateurDTO findById(@PathVariable("idUtilisateur") Integer id);

  @GetMapping(UTILISATEUR_ENDPOINT + "/find/{email}")
  UtilisateurDTO findByEmail(@PathVariable("email") String email);

  @GetMapping(UTILISATEUR_ENDPOINT + "/all")
  List<UtilisateurDTO> findAll();

  @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
  void delete(@PathVariable("idUtilisateur") Integer id);

}
