package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.CommandeClientDTO;
import com.ayed.gestionstock.entity.CommandeClient;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class commandeClientValidator {


  public static List<String> validate(CommandeClientDTO dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Veuillez renseigner le code de la commande");
      errors.add("Veuillez renseigner la date de la commande");
      errors.add("Veuillez renseigner l'etat de la commande");
      errors.add("Veuillez renseigner le client");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getCode())) {
      errors.add("Veuillez renseigner le code de la commande");
    }
    if (dto.getDateCommande() == null) {
      errors.add("Veuillez renseigner la date de la commande");
    }
    if (!StringUtils.hasLength(dto.getEtatCommande().toString())) {
      errors.add("Veuillez renseigner l'etat de la commande");
    }
    if (dto.getClient() == null || dto.getClient().getId() == null) {
      errors.add("Veuillez renseigner le client");
    }

    return errors;
  }

}
