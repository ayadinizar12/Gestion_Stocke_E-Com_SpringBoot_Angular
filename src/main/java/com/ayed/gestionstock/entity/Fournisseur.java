package com.ayed.gestionstock.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Fournisseur extends abstractEnt {

    private String nom;
    private String pernom;
    private String photo;
    private String email;
    private String numTel;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;

    private Integer idEntreprise;




}
