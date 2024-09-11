package com.ayed.gestionstock.service.strategyPhoto;

import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.service.ClientService;
import com.ayed.gestionstock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDTO> {

  private FlickrService flickrService;
  private ClientService clientService;

  @Autowired
  public SaveClientPhoto(FlickrService flickrService, ClientService clientService) {
    this.flickrService = flickrService;
    this.clientService = clientService;
  }

  @Override
  public ClientDTO savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {

    ClientDTO client = clientService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);

    if (!StringUtils.hasLength(urlPhoto)) {
      throw new invalidOperationException("Erreur lors de l'enregistrement de photo du client",
              errorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    client.setPhoto(urlPhoto);
    return clientService.save(client);
  }
}
