package com.ayed.gestionstock.service;

import com.ayed.gestionstock.dto.ArticleDTO;
import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;
import java.util.List;

public interface FlickrService {
 String savePhoto(InputStream photo,String titre) throws FlickrException;
}
