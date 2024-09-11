package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Data
@Builder

public class ArticleDTO{

    private Integer id;
    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaire;

    private BigDecimal tauxTVA;

    private BigDecimal prix;

    private String photo;

    private Integer idEntreprise;

    private CategoryDTO category;



}
