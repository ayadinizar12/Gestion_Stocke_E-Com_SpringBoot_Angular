package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.FournisseurDTO;
import com.ayed.gestionstock.entity.CommandeClient;
import com.ayed.gestionstock.entity.Entreprise;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.CommandeFournisseurRepo;
import com.ayed.gestionstock.repository.FournisseurRepo;
import com.ayed.gestionstock.service.FournisseurService;
import com.ayed.gestionstock.validator.fournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

  private CommandeFournisseurRepo commandeFournisseurRepository;
  private FournisseurRepo fournisseurRepository;
  private mappersDTO mappersDTo;

  @Autowired
  public FournisseurServiceImpl(CommandeFournisseurRepo commandeFournisseurRepository, FournisseurRepo fournisseurRepository, mappersDTO mappersDTo) {
      this.commandeFournisseurRepository = commandeFournisseurRepository;
      this.fournisseurRepository = fournisseurRepository;
      this.mappersDTo = mappersDTo;
  }

  @Override
  public FournisseurDTO save(FournisseurDTO fournisseurDTO) {
    List<String> errors = fournisseurValidator.validate(fournisseurDTO);
    if (!errors.isEmpty()) {
      log.error("Fournisseur is not valid {}", fournisseurDTO);
      throw new invalidEntityException("Le fournisseur n'est pas valide", errorCodes.FRN_NOT_VALID,errors);

    }

    return mappersDTo.fournisseurtoDTO(
        fournisseurRepository.save(
            mappersDTo.fournisseurtoEntity(fournisseurDTO)
        )
    );
  }

  @Override
  public FournisseurDTO findById(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return null;
    }
    return fournisseurRepository.findById(id)
        .map(mappersDTo::fournisseurtoDTO)
        .orElseThrow(
                () -> new entityNotFoundException(
            "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
            errorCodes.FRN_NOT_FOUND)
        );
  }

  @Override
  public List<FournisseurDTO> findAll() {
    return fournisseurRepository.findAll().stream()
        .map(mappersDTo::fournisseurtoDTO)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return;
    }
    List<CommandeClient> commandeFournisseur = commandeFournisseurRepository
            .findAllByFournisseurId(id);

    if (!commandeFournisseur.isEmpty()) {
      throw new invalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
          errorCodes.FRN_ALREADY_IN_USE);
    }

    fournisseurRepository.deleteById(id);
  }
}
