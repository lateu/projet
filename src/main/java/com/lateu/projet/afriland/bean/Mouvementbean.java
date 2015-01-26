/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.Mouvement;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.metier.ServiceArchivage;
import com.lateu.projet.afriland.metier.ServiceMouvement;
import com.lateu.projet.afriland.metier.ServiceService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
public class Mouvementbean {

    @ManagedProperty(value = "#{ServiceMouvement}")
    private ServiceMouvement serviceMouvement;
//    @ManagedProperty(value = "#{ServiceService}")
//    private ServiceService serviceService;
//    @ManagedProperty(value = "#{ServiceArchivage}")
//    private ServiceArchivage serviceArchivage;
    public List<Mouvement> mouvements;
    
 User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername();

    public Mouvementbean() {
    }

   
    public ServiceMouvement getServiceMouvement() {
        return serviceMouvement;
    }

    public void setServiceMouvement(ServiceMouvement serviceMouvement) {
        this.serviceMouvement = serviceMouvement;
    }

    public List<Mouvement> getMouvements() {
        if(name!=null){
        return mouvements = serviceMouvement.findAll();
    }
        return null;
    }
    public void setMouvements(List<Mouvement> mouvements) {
        this.mouvements = mouvements;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
}
