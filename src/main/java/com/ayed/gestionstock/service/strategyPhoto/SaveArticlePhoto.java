package com.ayed.gestionstock.service.strategyPhoto;

import com.ayed.gestionstock.dto.ArticleDTO;
import com.ayed.gestionstock.exception.errorCodes;
import com.ayed.gestionstock.exception.invalidOperationException;
import com.ayed.gestionstock.service.ArticleService;
import com.ayed.gestionstock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDTO> {

  private FlickrService flickrService;
  private ArticleService articleService;

  @Autowired
  public SaveArticlePhoto(FlickrService flickrService, ArticleService articleService) {
    this.flickrService = flickrService;
    this.articleService = articleService;
  }

  @Override
  public ArticleDTO savePhoto(Integer id, InputStream photo, String titre)
          throws FlickrException {
    ArticleDTO article = articleService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new invalidOperationException("Erreur lors de l'enregistrement de photo de l'article"
              , errorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    article.setPhoto(urlPhoto);
    return articleService.save(article);
  }
}
