package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Client;
import com.ayed.gestionstock.entity.EtatCommande;
import com.ayed.gestionstock.entity.LigneCommandeClient;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder
public class CommandeClientDTO {

    private Integer id;
    private String code;
    private Instant dateCommande;
    private ClientDTO client;
    private List<LigneCommandeClientDTO> ligneCommandeClients;
    private EtatCommande etatCommande;
    private Integer idEntreprise;

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
