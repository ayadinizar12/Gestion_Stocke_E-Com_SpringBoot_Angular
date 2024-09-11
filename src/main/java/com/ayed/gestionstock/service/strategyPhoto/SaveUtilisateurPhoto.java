package com.ayed.gestionstock.service.strategyPhoto;


import com.ayed.gestionstock.dto.UtilisateurDTO;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.service.FlickrService;
import com.ayed.gestionstock.service.UtilisateurService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDTO> {

  private FlickrService flickrService;
  private UtilisateurService utilisateurService;

  @Autowired
  public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
    this.flickrService = flickrService;
    this.utilisateurService = utilisateurService;
  }

  @Override
  public UtilisateurDTO savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    UtilisateurDTO utilisateur = utilisateurService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new invalidOperationException("Erreur lors de l'enregistrement de photo de l'utilisateur",
              errorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    utilisateur.setPhoto(urlPhoto);
    return utilisateurService.save(utilisateur);
  }
}
