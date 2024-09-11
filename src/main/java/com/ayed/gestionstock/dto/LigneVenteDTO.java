package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.entity.Ventes;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter  @Data
 @Builder

public class LigneVenteDTO  {

    private Integer id;
    private VentesDTO ventes;
    private BigDecimal quantite;
    private ArticleDTO article;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;
}
