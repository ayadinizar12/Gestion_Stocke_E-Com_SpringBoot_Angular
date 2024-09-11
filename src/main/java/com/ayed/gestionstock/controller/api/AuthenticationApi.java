package com.ayed.gestionstock.controller.api;

import com.ayed.gestionstock.dto.auth.AuthenticationRequest;
import com.ayed.gestionstock.dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ayed.gestionstock.utils.Constants.AUTHENTICATION_ENDPOINT;

@Api("authentication")
public interface AuthenticationApi {

  @PostMapping(AUTHENTICATION_ENDPOINT + "/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request);

}
