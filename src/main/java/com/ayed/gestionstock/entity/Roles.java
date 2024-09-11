package com.ayed.gestionstock.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter @Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Roles extends abstractEnt {



    private String roleName;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;


}
