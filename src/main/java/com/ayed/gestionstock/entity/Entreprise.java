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
@Getter@Setter @Entity @Data
@EqualsAndHashCode(callSuper = true)
public class Entreprise extends abstractEnt {


    private String nom;


    private String description;

    @Embedded
    private Adresse adresse;


    private String codeFiscal;


    private String photo;

    private String email;


    private String numTel;


    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;


}
