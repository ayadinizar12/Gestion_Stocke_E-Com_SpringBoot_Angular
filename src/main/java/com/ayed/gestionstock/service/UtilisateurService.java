package com.ayed.gestionstock.service;


import com.ayed.gestionstock.dto.ChangerMotDePasseUtilisateurDTO;
import com.ayed.gestionstock.dto.UtilisateurDTO;
import java.util.List;

public interface UtilisateurService {

  UtilisateurDTO save(UtilisateurDTO utilisateurDTO);

  UtilisateurDTO findById(Integer id);

  List<UtilisateurDTO> findAll();

  void delete(Integer id);
  
  UtilisateurDTO changerMotDePasse(ChangerMotDePasseUtilisateurDTO dto);

  UtilisateurDTO findByEmail(String email);
}
