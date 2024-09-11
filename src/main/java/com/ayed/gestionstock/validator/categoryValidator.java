package com.ayed.gestionstock.validator;

import com.ayed.gestionstock.dto.CategoryDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class categoryValidator {

    public static List<String> validate(CategoryDTO categoryDTO){
        List<String> errors= new ArrayList<>();

        if (categoryDTO==null || !StringUtils.hasLength(categoryDTO.getCode())){
            errors.add("error Code categorie");
        }
        return errors;
    }
}
