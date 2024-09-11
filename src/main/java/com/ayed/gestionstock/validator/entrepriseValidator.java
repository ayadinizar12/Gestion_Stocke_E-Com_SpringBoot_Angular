package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.EntrepriseDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class entrepriseValidator {

    public static List<String> validate(EntrepriseDTO dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez reseigner la description de l'entreprise");
            errors.add("Veuillez reseigner le code fiscal de l'entreprise");
            errors.add("Veuillez reseigner l'email de l'entreprise");
            errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
            errors.addAll(adresseValidator.validate(null));
            return errors;
        }

        if (!StringUtils.hasLength(dto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getDescription())) {
            errors.add("Veuillez reseigner la description de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getCodeFiscal())) {
            errors.add("Veuillez reseigner le code fiscal de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getEmail())) {
            errors.add("Veuillez reseigner l'email de l'entreprise");
        }
        if (!StringUtils.hasLength(dto.getNumTel())) {
            errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
        }

        errors.addAll(adresseValidator.validate(dto.getAdresse()));
        return errors;
    }

}
