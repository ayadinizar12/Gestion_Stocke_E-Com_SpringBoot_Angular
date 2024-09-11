package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.EtatCommande;
import com.ayed.gestionstock.entity.Fournisseur;
import com.ayed.gestionstock.entity.LigneCommandeFournisseur;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data @Builder
public class CommandeFournisseurDTO {

    private Integer id;
    private String code;
    private Instant dateCommande;
    private FournisseurDTO fournisseur;
    private List<LigneCommandeFournisseurDTO> ligneCommandeFournisseurs;
    private EtatCommande etatCommande;
    private Integer idEntreprise;


    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande) ;
    }
}
