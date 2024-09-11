package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.LigneVente;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder

public class VentesDTO {

    private Integer id;
    private String code;
    private Instant dateVente;
    private String commentaire;
    private Integer idEntreprise;
    private List<LigneVenteDTO> ligneVentes;

}
