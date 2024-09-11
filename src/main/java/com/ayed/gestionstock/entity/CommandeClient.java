package com.ayed.gestionstock.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data @Entity
@EqualsAndHashCode(callSuper = true)

public class CommandeClient extends abstractEnt {

    private String code;
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @OneToMany(mappedBy = "CommandeClient")
    private List<LigneCommandeClient> ligneCommandeClients;


    @Column(name = "etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    private Integer idEntreprise;

}
