package com.ayed.gestionstock.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter  @Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class LigneVente extends abstractEnt {

    @ManyToOne
    @JoinColumn(name = "idventes")
    private Ventes ventes;

    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    private BigDecimal prixUnitaire;
    private Integer idEntreprise;
}
