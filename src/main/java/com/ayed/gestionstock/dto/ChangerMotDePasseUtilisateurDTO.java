package com.ayed.gestionstock.dto;

import lombok.*;

@Builder @Data @Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ChangerMotDePasseUtilisateurDTO {
    private Integer id;

    private String motDePasse;

    private String confirmMotDePasse;
}
