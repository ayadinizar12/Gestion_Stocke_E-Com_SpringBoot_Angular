package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Adresse;
import com.ayed.gestionstock.entity.Entreprise;
import com.ayed.gestionstock.entity.Roles;
import com.ayed.gestionstock.entity.abstractEnt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder

public class UtilisateurDTO{

    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Instant dateDeNaissance;
    private String moteDePasse;
    private AdresseDTO adresse;
    private String photo;
    private EntrepriseDTO entreprise;
    private List<RolesDTO> roles;
}
