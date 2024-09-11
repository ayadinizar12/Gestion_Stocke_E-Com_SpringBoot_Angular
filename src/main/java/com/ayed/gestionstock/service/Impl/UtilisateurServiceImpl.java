package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.ChangerMotDePasseUtilisateurDTO;
import com.ayed.gestionstock.dto.UtilisateurDTO;
import com.ayed.gestionstock.entity.Utilisateur;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.UtilisateurRepo;
import com.ayed.gestionstock.service.UtilisateurService;
import com.ayed.gestionstock.validator.utilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

  private UtilisateurRepo utilisateurRepository;
  private mappersDTO mappersDTo;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UtilisateurServiceImpl(UtilisateurRepo utilisateurRepository, mappersDTO mappersDTo, PasswordEncoder passwordEncoder) {
    this.utilisateurRepository = utilisateurRepository;
    this.mappersDTo = mappersDTo;
      this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UtilisateurDTO save(UtilisateurDTO utilisateurDTO) {
    List<String> errors = utilisateurValidator.validate(utilisateurDTO);
    if (!errors.isEmpty()) {
      log.error("Utilisateur is not valid {}", utilisateurDTO);
      throw new invalidEntityException("L'utilisateur n'est pas valide",
              errorCodes.UTL_NOT_VALID, errors);
    }

    if(userAlreadyExists(utilisateurDTO.getEmail())) {
      throw new invalidEntityException("Un autre utilisateur avec le meme email existe deja",
              errorCodes.UTL_ALREADY_EXISTS,
              Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD")
      );
    }


    utilisateurDTO.setMoteDePasse(passwordEncoder.encode(utilisateurDTO.getMoteDePasse()));

    return mappersDTo.utilisateurtoDTO(utilisateurRepository
            .save(mappersDTo.utilisateurtoEntity(utilisateurDTO)
            )
    );
  }

  private boolean userAlreadyExists(String email) {
    Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
    return user.isPresent();
  }

  @Override
  public UtilisateurDTO findById(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return null;
    }
    return utilisateurRepository
            .findById(id)
            .map(mappersDTo::utilisateurtoDTO)
            .orElseThrow(() -> new entityNotFoundException(
                    "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
                    errorCodes.UTL_NOT_FOUND)
            );
  }

  @Override
  public List<UtilisateurDTO> findAll() {
    return utilisateurRepository
            .findAll()
            .stream()
            .map(mappersDTo::utilisateurtoDTO)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return;
    }
    utilisateurRepository.deleteById(id);
  }

  @Override
  public UtilisateurDTO findByEmail(String email) {
    return utilisateurRepository
            .findUtilisateurByEmail(email)
            .map(mappersDTo::utilisateurtoDTO)
            .orElseThrow(() -> new entityNotFoundException(
                    "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD",
                    errorCodes.UTL_NOT_FOUND)
            );
  }

  @Override
  public UtilisateurDTO changerMotDePasse(ChangerMotDePasseUtilisateurDTO
                                                    changerMotDePasseUtilisateurDTO){

    validate(changerMotDePasseUtilisateurDTO);

    Optional<Utilisateur> utilisateurOptional = utilisateurRepository
            .findById(changerMotDePasseUtilisateurDTO.getId());

    if (utilisateurOptional.isEmpty()) {
      log.warn("Aucun utilisateur n'a ete trouve avec l'ID " + changerMotDePasseUtilisateurDTO.getId());
      throw new entityNotFoundException("Aucun utilisateur n'a ete trouve avec l'ID "
              + changerMotDePasseUtilisateurDTO.getId(), errorCodes.UTL_NOT_FOUND);
    }

    Utilisateur utilisateur = utilisateurOptional.get();
    utilisateur.setMoteDePasse(passwordEncoder.encode(changerMotDePasseUtilisateurDTO.getMotDePasse()));

    return mappersDTo.utilisateurtoDTO(utilisateurRepository.save(utilisateur));
  }

  private void validate(ChangerMotDePasseUtilisateurDTO dto) {
    if (dto == null) {
      log.warn("Impossible de modifier le mot de passe avec un objet NULL");
      throw new invalidOperationException("Aucune information n'a ete fourni pour pouvoir changer le mot de passe",
          errorCodes.UTL_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
    if (dto.getId() == null) {
      log.warn("Impossible de modifier le mot de passe avec un ID NULL");
      throw new invalidOperationException("ID utilisateur null:: Impossible de modifier le mote de passe",
          errorCodes.UTL_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
    if (!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmMotDePasse())) {
      log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
      throw new invalidOperationException("Mot de passe utilisateur null:: Impossible de modifier le mote de passe",
          errorCodes.UTL_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
    if (!dto.getMotDePasse().equals(dto.getConfirmMotDePasse())) {
      log.warn("Impossible de modifier le mot de passe avec deux mots de passe different");
      throw new invalidOperationException("Mots de passe utilisateur non conformes:: Impossible de modifier le mote de passe",
          errorCodes.UTL_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
  }
}
