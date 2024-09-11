package com.ayed.gestionstock.service.Impl;

import com.ayed.gestionstock.dto.AdresseDTO;
import com.ayed.gestionstock.dto.EntrepriseDTO;
import com.ayed.gestionstock.dto.RolesDTO;
import com.ayed.gestionstock.dto.UtilisateurDTO;
import com.ayed.gestionstock.entity.Client;
import com.ayed.gestionstock.entity.Entreprise;
import com.ayed.gestionstock.exception.entityNotFoundException;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidEntityException;
import com.ayed.gestionstock.mappers.mappersDTO;
import com.ayed.gestionstock.repository.EntrepriseRepo;
import com.ayed.gestionstock.repository.RolesRepo;
import com.ayed.gestionstock.service.EntrepriseService;
import com.ayed.gestionstock.service.UtilisateurService;
import com.ayed.gestionstock.validator.entrepriseValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepo entrepriseRepo;
    private mappersDTO mappersDTo;
    private RolesRepo rolesRepository;
    private UtilisateurService utilisateurService;

    public EntrepriseServiceImpl(EntrepriseRepo entrepriseRepo, mappersDTO mappersDTo, RolesRepo rolesRepository, UtilisateurService utilisateurService) {
        this.entrepriseRepo = entrepriseRepo;
        this.mappersDTo = mappersDTo;
        this.rolesRepository = rolesRepository;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public EntrepriseDTO save(EntrepriseDTO entrepriseDTO) {
        List<String> errors = entrepriseValidator.validate(entrepriseDTO);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", entrepriseDTO);
            throw new invalidEntityException("L'entreprise n'est pas valide",
                    errorCodes.ENT_NOT_VALID,errors);
        }
        EntrepriseDTO savedEntreprise = mappersDTo.entreprisetoDTO(
                entrepriseRepo.save(
                        mappersDTo.entreprisetoEntity(entrepriseDTO)));

        UtilisateurDTO utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurDTO savedUser = utilisateurService.save(utilisateur);

        RolesDTO rolesDto = RolesDTO.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        rolesRepository.save(mappersDTo.rolestoEntity(rolesDto));

        return  savedEntreprise;
    }

    private UtilisateurDTO fromEntreprise(EntrepriseDTO dto) {
        return UtilisateurDTO.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .moteDePasse(generateRandomPassword())
                .entreprise(dto)
                .dateDeNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }

    @Override
    public EntrepriseDTO findById(Integer id) {
        if (id==null){
            log.error("Entreprise id is null");
            return null;
        }

        Optional< Entreprise> entreprise=entrepriseRepo.findById(id);
        return Optional.of(mappersDTo.entreprisetoDTO(entreprise.get()))
                .orElseThrow(()-> new entityNotFoundException("Aucun Entreprise avec l'id = "
                        +id+" dans BD",errorCodes.ENT_NOT_FOUND)

                );
    }

    @Override
    public List<EntrepriseDTO> findAll() {
        return entrepriseRepo.findAll()
                .stream()
                .map(mappersDTo::entreprisetoDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepo.deleteById(id);
    }

}
