package com.ayed.gestionstock.dto;

import com.ayed.gestionstock.entity.Adresse;
import com.ayed.gestionstock.entity.CommandeClient;
import com.ayed.gestionstock.entity.abstractEnt;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data @Builder
public class ClientDTO  {

    private Integer id;
    private String nom;
    private String pernom;
    private String photo;
    private String email;
    private String numTel;
    private AdresseDTO adresse;

    private List<CommandeClientDTO> commandeClients;

    private Integer idEntreprise;


}
