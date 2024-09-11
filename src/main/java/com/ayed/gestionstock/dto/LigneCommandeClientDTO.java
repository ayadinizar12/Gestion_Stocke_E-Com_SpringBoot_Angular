package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.entity.CommandeClient;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor @Builder
@Getter@Setter @Data
public class LigneCommandeClientDTO {

    private Integer id;
    private ArticleDTO article;
    private CommandeClientDTO commandeClient;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;

}
