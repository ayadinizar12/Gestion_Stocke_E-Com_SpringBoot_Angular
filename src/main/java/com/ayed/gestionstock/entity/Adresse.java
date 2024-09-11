package com.ayed.gestionstock.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@EqualsAndHashCode
@Embeddable

public class Adresse {

    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostale;
    private String pays;

}
