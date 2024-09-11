package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.CategoryDTO;
import com.ayed.gestionstock.dto.ClientDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class clientValidator {

    public static List<String> validate(ClientDTO clientDTO){
        List<String> errors= new ArrayList<>();

        if (clientDTO==null || !StringUtils.hasLength(clientDTO.getNom())){
            errors.add("error Nom client");
        }
        if (!StringUtils.hasLength(clientDTO.getPernom())){
            errors.add("error Prenom client");
        }
        if (!StringUtils.hasLength(clientDTO.getEmail())){
            errors.add("error Email client");
        }
        if (!StringUtils.hasLength(clientDTO.getNumTel())){
            errors.add("error Tel client");
        }
        return errors;
    }
}
