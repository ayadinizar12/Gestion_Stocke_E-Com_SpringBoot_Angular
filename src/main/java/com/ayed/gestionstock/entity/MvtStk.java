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
@Getter@Setter @Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class MvtStk extends abstractEnt {

    private Instant dateMvt;

    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    @Column(name = "typemvt")
    @Enumerated(EnumType.STRING)
    private TypeMvtStk typeMvt;

    @Column(name = "sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMvtStk sourceMvt;


    private Integer idEntreprise;
}
