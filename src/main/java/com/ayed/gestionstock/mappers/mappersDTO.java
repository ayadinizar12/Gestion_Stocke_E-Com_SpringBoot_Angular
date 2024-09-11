package com.ayed.gestionstock.mappers;

import com.ayed.gestionstock.dto.*;
import com.ayed.gestionstock.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class mappersDTO {

    //Article
    public ArticleDTO articletoDTO(Article article){
        if (article==null){
            //TODO
        }
        ArticleDTO articleDTO=new ArticleDTO();
        BeanUtils.copyProperties(article,articleDTO);
        return articleDTO;
    }
    public Article articletoEntity(ArticleDTO articleDTO) {
        Article article=new Article();
        BeanUtils.copyProperties(articleDTO,article);
        return article;
    }


    //Category
    public CategoryDTO categorytoDTO(Category category){
        if (category==null){
            //TODO
        }
        CategoryDTO categoryDTO=new CategoryDTO();
        BeanUtils.copyProperties(category,categoryDTO);
        return categoryDTO;
    }
    public Category categorytoEntity(CategoryDTO categoryDTO) {
        Category category=new Category() ;
        BeanUtils.copyProperties(categoryDTO ,category);
        return category;
    }

    //Client
    public ClientDTO clienttoDTO(Client client){
        if (client==null){
            //TODO
        }
        ClientDTO clientDTO=new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        return clientDTO;
    }
    public Client clienttoEntity(ClientDTO clientDTO) {
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return client;
    }


    //CommandeClient
    public CommandeClientDTO commandeClienttoDTO(CommandeClient commandeClient){
        if (commandeClient==null){
            //TODO
        }
        CommandeClientDTO commandeClientDTO=new CommandeClientDTO();
        BeanUtils.copyProperties(commandeClient,commandeClientDTO);
        return commandeClientDTO;
    }
    public CommandeClient commandeClienttoEntity(CommandeClientDTO commandeClientDTO) {
        CommandeClient commandeClient=new CommandeClient();
        BeanUtils.copyProperties(commandeClientDTO,commandeClient);
        return commandeClient;
    }


    //CommandeFournisseur
    public CommandeFournisseurDTO commandeFournisseurtoDTO(CommandeFournisseur commandeFournisseur){
        if (commandeFournisseur==null){
            //TODO
        }
        CommandeFournisseurDTO commandeFournisseurDTO=new CommandeFournisseurDTO();
        BeanUtils.copyProperties(commandeFournisseur,commandeFournisseurDTO);
        return commandeFournisseurDTO;
    }
    public CommandeFournisseur commandeFournisseurtoEntity(CommandeFournisseurDTO commandeFournisseurDTO) {
        CommandeFournisseur commandeFournisseur=new CommandeFournisseur();
        BeanUtils.copyProperties(commandeFournisseurDTO,commandeFournisseur);
        return commandeFournisseur;
    }


    //Entreprise
    public EntrepriseDTO entreprisetoDTO(Entreprise entreprise){
        if (entreprise==null){
            //TODO
        }
        EntrepriseDTO entrepriseDTO=new EntrepriseDTO();
        BeanUtils.copyProperties(entreprise,entrepriseDTO);
        return entrepriseDTO;
    }
    public Entreprise entreprisetoEntity(EntrepriseDTO entrepriseDTO) {
        Entreprise entreprise= new Entreprise();
        BeanUtils.copyProperties(entrepriseDTO,entreprise);
        return entreprise;
    }


    //Fournisseur
    public FournisseurDTO fournisseurtoDTO(Fournisseur fournisseur){
        if (fournisseur==null){
            //TODO
        }
        FournisseurDTO fournisseurDTO=new FournisseurDTO();
        BeanUtils.copyProperties(fournisseur,fournisseurDTO);
        return fournisseurDTO;
    }
    public Fournisseur fournisseurtoEntity(FournisseurDTO fournisseurDTO) {
        Fournisseur fournisseur= new Fournisseur();
        BeanUtils.copyProperties(fournisseurDTO,fournisseur);
        return fournisseur;
    }


    //LigneCommandeClient
    public LigneCommandeClientDTO ligneCommandeClienttoDTO(LigneCommandeClient ligneCommandeClient){
        if (ligneCommandeClient==null){
            //TODO
        }
        LigneCommandeClientDTO ligneCommandeClientDTO=new LigneCommandeClientDTO();
        BeanUtils.copyProperties(ligneCommandeClient,ligneCommandeClientDTO);
        return ligneCommandeClientDTO;
    }
    public LigneCommandeClient ligneCommandeClienttoEntity(LigneCommandeClientDTO ligneCommandeClientDTO) {
        LigneCommandeClient ligneCommandeClient=new LigneCommandeClient();
        BeanUtils.copyProperties(ligneCommandeClientDTO,ligneCommandeClient);
        return ligneCommandeClient;
    }


    //LigneCommandeFournisseur
    public LigneCommandeFournisseurDTO ligneCommandeFournisseurtoDTO(LigneCommandeFournisseur ligneCommandeFournisseur){
        if (ligneCommandeFournisseur==null){
            //TODO
        }
        LigneCommandeFournisseurDTO ligneCommandeFournisseurDTO=new LigneCommandeFournisseurDTO();
        BeanUtils.copyProperties(ligneCommandeFournisseur,ligneCommandeFournisseurDTO);
        return ligneCommandeFournisseurDTO;
    }
    public LigneCommandeFournisseur ligneCommandeFournisseurtoEntity(LigneCommandeFournisseurDTO ligneCommandeFournisseurDTO) {
        LigneCommandeFournisseur ligneCommandeFournisseur=new LigneCommandeFournisseur();
        BeanUtils.copyProperties(ligneCommandeFournisseurDTO,ligneCommandeFournisseur);
        return ligneCommandeFournisseur;
    }


    //LigneVente
    public LigneVenteDTO ligneVentetoDTO(LigneVente ligneVente){
        if (ligneVente==null){
            //TODO
        }
        LigneVenteDTO ligneVenteDTO=new LigneVenteDTO();
        BeanUtils.copyProperties(ligneVente,ligneVenteDTO);
        return ligneVenteDTO;
    }
    public LigneVente ligneVentetoEntity(LigneVenteDTO ligneVenteDTO) {
        LigneVente ligneVente =new LigneVente();
        BeanUtils.copyProperties(ligneVenteDTO ,ligneVente);
        return ligneVente;
    }

    //MvtStk
    public MvtStkDTO mvtStktoDTO(MvtStk mvtStk){
        if (mvtStk==null){
            //TODO
        }
        MvtStkDTO mvtStkDTO=new MvtStkDTO();
        BeanUtils.copyProperties(mvtStk,mvtStkDTO);
        return mvtStkDTO;
    }
    public MvtStk mvtStktoEntity(MvtStkDTO mvtStkDTO) {
        MvtStk mvtStk=new MvtStk();
        BeanUtils.copyProperties(mvtStkDTO,mvtStk);
        return mvtStk;
    }


    //Roles
    public RolesDTO rolestoDTO(Roles roles){
        if (roles==null){
            //TODO
        }
        RolesDTO rolesDTO=new RolesDTO();
        BeanUtils.copyProperties(roles,rolesDTO);
        return rolesDTO;
    }
    public Roles rolestoEntity(RolesDTO rolesDTO) {
        Roles roles=new Roles();
        BeanUtils.copyProperties(rolesDTO,roles);
        return roles;
    }



    //Utilisateur
    public UtilisateurDTO utilisateurtoDTO(Utilisateur utilisateur){
        if (utilisateur==null){
            //TODO
        }
        UtilisateurDTO utilisateurDTO=new UtilisateurDTO();
        BeanUtils.copyProperties(utilisateur,utilisateurDTO);
        return utilisateurDTO;
    }
    public Utilisateur utilisateurtoEntity(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur=new Utilisateur();
        BeanUtils.copyProperties(utilisateurDTO,utilisateur);
        return utilisateur;
    }


    //Ventes
    public VentesDTO ventestoDTO( Ventes ventes){
        if (ventes==null){
            //TODO
        }
        VentesDTO ventesDTO=new VentesDTO();
        BeanUtils.copyProperties(ventes,ventesDTO);
        return ventesDTO;
    }
    public Ventes ventestoEntity(VentesDTO ventesDTO) {
        Ventes ventes= new Ventes();
        BeanUtils.copyProperties(ventesDTO,ventes);
        return ventes;
    }

}
