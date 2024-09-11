package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.ArticleDTO;
import com.ayed.gestionstock.dto.CategoryDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class articleValidator {

    public static List<String> validate(ArticleDTO articleDTO){
        List<String> errors= new ArrayList<>();

        if (articleDTO ==null || !StringUtils.hasLength(articleDTO.getCodeArticle())){
            errors.add("error Code Article");
        }
        if (!StringUtils.hasLength(articleDTO.getDesignation())){
            errors.add("error Designation Article");
        }
        if (articleDTO.getPrix()==null){
            errors.add("error prix Article");
        }
        if (articleDTO.getPrixUnitaire()==null){
            errors.add("error Prix Unitaire Article");
        }
        if (articleDTO.getTauxTVA()==null){
            errors.add("error TVA Article");
        }
        if (articleDTO.getCategory()==null){
            errors.add("error Category Article");
        }
        return errors;
    }
}
