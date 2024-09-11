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

public class Category extends abstractEnt {

    private String code;

    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    private Integer idEntreprise;

}
