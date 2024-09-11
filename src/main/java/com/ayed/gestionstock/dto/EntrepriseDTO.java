package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Adresse;
import com.ayed.gestionstock.entity.Utilisateur;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor @Builder
@Getter@Setter @Data
public class EntrepriseDTO {

    private Integer id;
    private String nom;
    private String description;
    private AdresseDTO adresse;
    private String codeFiscal;
    private String photo;
    private String email;
    private String numTel;
    private String siteWeb;
    private List<UtilisateurDTO> utilisateurs;


}
