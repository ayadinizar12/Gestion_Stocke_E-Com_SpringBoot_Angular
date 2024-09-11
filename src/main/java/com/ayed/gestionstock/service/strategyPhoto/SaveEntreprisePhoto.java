package com.ayed.gestionstock.service.strategyPhoto;

import com.ayed.gestionstock.dto.EntrepriseDTO;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.service.EntrepriseService;
import com.ayed.gestionstock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDTO> {

  private FlickrService flickrService;
  private EntrepriseService entrepriseService;

  @Autowired
  public SaveEntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
    this.flickrService = flickrService;
    this.entrepriseService = entrepriseService;
  }

  @Override
  public EntrepriseDTO savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    EntrepriseDTO entreprise = entrepriseService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new invalidOperationException("Erreur lors de l'enregistrement de photo de l'entreprise",
              errorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    entreprise.setPhoto(urlPhoto);
    return entrepriseService.save(entreprise);
  }
}
