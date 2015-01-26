/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceAgence;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
public class Agencebean implements Serializable{
 @ManagedProperty(value = "#{ServiceAgence}")
 private ServiceAgence serviceAgence;
 private Agence agenceSelect=new Agence();
 public List<Agence>agences; 
 User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername();

    public Agencebean() {
    }
    
    
    public void create(){
     serviceAgence.create(agenceSelect);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dossier Enregistré", ""));
        
    
    }

    public ServiceAgence getServiceAgence() {
        return serviceAgence;
    }

    public void setServiceAgence(ServiceAgence serviceAgence) {
        this.serviceAgence = serviceAgence;
    }

    public Agence getAgenceSelect() {
        return agenceSelect;
    }

    public void setAgenceSelect(Agence agenceSelect) {
        this.agenceSelect = agenceSelect;
    }

    public List<Agence> getAgences() {
        return agences=serviceAgence.findAll();
    }

    public void setAgences(List<Agence> agences) {
        this.agences = agences;
    }
    
    
    
}
