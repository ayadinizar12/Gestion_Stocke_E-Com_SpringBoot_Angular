package com.ayed.gestionstock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@Getter@Setter
@Data
@EntityListeners(AuditingEntityListener.class)
public class abstractEnt implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name="creationDate" , nullable = false,updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")

    private Instant lastModifiedDate;
}
