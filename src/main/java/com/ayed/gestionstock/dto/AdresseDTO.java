package com.ayed.gestionstock.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Builder
public class AdresseDTO {

    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostale;
    private String pays;

}
