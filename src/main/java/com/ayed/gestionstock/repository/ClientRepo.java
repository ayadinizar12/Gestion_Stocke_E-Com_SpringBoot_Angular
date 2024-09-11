package com.ayed.gestionstock.repository;

import com.ayed.gestionstock.dto.ClientDTO;
import com.ayed.gestionstock.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client,Integer> {


}
