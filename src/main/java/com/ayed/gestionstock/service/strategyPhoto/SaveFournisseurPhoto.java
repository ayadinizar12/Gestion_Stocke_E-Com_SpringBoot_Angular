package com.ayed.gestionstock.service.strategyPhoto;

import com.ayed.gestionstock.dto.FournisseurDTO;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.service.FlickrService;
import com.ayed.gestionstock.service.FournisseurService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("fournisseurStrategy")
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurDTO> {

  private FlickrService flickrService;
  private FournisseurService fournisseurService;

  @Autowired
  public SaveFournisseurPhoto(FlickrService flickrService, FournisseurService fournisseurService) {
    this.flickrService = flickrService;
    this.fournisseurService = fournisseurService;
  }

  @Override
  public FournisseurDTO savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    FournisseurDTO fournisseur = fournisseurService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new invalidOperationException("Erreur lors de l'enregistrement de photo du fournisseur"
              , errorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    fournisseur.setPhoto(urlPhoto);
    return fournisseurService.save(fournisseur);
  }
}
