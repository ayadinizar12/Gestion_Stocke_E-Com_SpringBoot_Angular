package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder
public class CategoryDTO {

    private Integer id;
    private String code;

    private String designation;

    private List<ArticleDTO> articles;

    private Integer idEntreprise;

}
