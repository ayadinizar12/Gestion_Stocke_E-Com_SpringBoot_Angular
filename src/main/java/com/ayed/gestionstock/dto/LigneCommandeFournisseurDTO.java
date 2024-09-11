package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.entity.CommandeFournisseur;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder
public class LigneCommandeFournisseurDTO {

    private Integer id;
    private Article article;
    private CommandeFournisseurDTO commandeFournisseur;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;



}
