package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.VentesDTO;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ventesValidator {

  public static List<String> validate(VentesDTO dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Veuillez renseigner le code de la commande");
      errors.add("Veuillez renseigner la date de la commande");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getCode())) {
      errors.add("Veuillez renseigner le code de la commande");
    }
    if (dto.getDateVente() == null) {
      errors.add("Veuillez renseigner la date de la commande");
    }

    return errors;
  }

}
