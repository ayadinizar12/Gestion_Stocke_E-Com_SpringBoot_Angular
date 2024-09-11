package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Utilisateur;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder

public class RolesDTO {

    private Integer id;
    private String roleName;
    private UtilisateurDTO utilisateur;


}
