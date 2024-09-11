package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.CategoryDTO;
import com.ayed.gestionstock.dto.UtilisateurDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class utilisateurValidator {

    public static List<String> validate(UtilisateurDTO utilisateurDTO){
        List<String> errors= new ArrayList<>();

        if (utilisateurDTO==null){
            errors.add("error Nom Utilisateur");
            errors.add("error Prenom Utilisateur");
            errors.add("error Email Utilisateur");
            errors.add("error Password Utilisateur");
            errors.add("error Adresse Utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getNom())){
            errors.add("error Nom Utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getPrenom())){
            errors.add("error Prenom Utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getEmail())){
            errors.add("error Email Utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDTO.getMoteDePasse())){
            errors.add("error Password Utilisateur");
        }
        if (utilisateurDTO.getDateDeNaissance()==null){
            errors.add("error Date Naissance Utilisateur");
        }
        if (utilisateurDTO.getAdresse()==null){
            errors.add("error Adresse Utilisateur");
        }else {
            if (!StringUtils.hasLength(utilisateurDTO.getAdresse().getAdresse1())) {
                errors.add("error Adresse1 Utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDTO.getAdresse().getVille())) {
                errors.add("error Ville Utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDTO.getAdresse().getCodePostale())) {
                errors.add("error Code Postale Utilisateur");
            }
            if (!StringUtils.hasLength(utilisateurDTO.getAdresse().getPays())) {
                errors.add("error Pays Utilisateur");
            }
        }
        return errors;
    }
}
