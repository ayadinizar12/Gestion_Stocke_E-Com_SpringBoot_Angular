package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.dto.FournisseurDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class fournisseurValidator {

    public static List<String> validate(FournisseurDTO fournisseurDTO){
        List<String> errors= new ArrayList<>();

        if (fournisseurDTO==null || !StringUtils.hasLength(fournisseurDTO.getNom())){
            errors.add("error Nom fournisseur ");
        }
        if (!StringUtils.hasLength(fournisseurDTO.getPernom())){
            errors.add("error Prenom fournisseur ");
        }
        if (!StringUtils.hasLength(fournisseurDTO.getEmail())){
            errors.add("error Email fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDTO.getNumTel())){
            errors.add("error Tel fournisseur");
        }
        return errors;
    }
}
