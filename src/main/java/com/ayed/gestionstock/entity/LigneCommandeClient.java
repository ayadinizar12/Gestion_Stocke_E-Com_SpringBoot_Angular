package com.ayed.gestionstock.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Entity @EqualsAndHashCode(callSuper = true)
public class LigneCommandeClient extends abstractEnt {

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idCommandeClient")
    private CommandeClient commandeClient;


    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;

}
