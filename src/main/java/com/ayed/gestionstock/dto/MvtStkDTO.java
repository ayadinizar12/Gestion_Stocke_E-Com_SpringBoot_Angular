package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Article;
import com.ayed.gestionstock.entity.SourceMvtStk;
import com.ayed.gestionstock.entity.TypeMvtStk;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder

public class MvtStkDTO {

    private Integer id;
    private Instant dateMvt;
    private BigDecimal quantite;
    private ArticleDTO article;
    private TypeMvtStk typeMvt;
    private SourceMvtStk sourceMvt;
    private Integer idEntreprise;
}
