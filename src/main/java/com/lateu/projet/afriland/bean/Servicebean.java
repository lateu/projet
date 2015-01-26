/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.metier.ServiceAgence;
import com.lateu.projet.afriland.metier.ServiceService;
import java.io.Serializable;
import java.util.ArrayList;
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
public class Servicebean implements Serializable {

    @ManagedProperty(value = "#{ServiceService}")
    private ServiceService serviceService;
    @ManagedProperty(value = "#{ServiceAgence}")
    private ServiceAgence serviceAgence;
    private List<Service> services = new ArrayList<Service>();
    private Service serviceselect = new Service();
    private String nomService;
    private String codeAgence;
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername();

    public Servicebean() {
    }

    public void DeleteService(){
    serviceService.DeleteService(serviceselect);
    }
    
    
    public void createService() {
        if ((serviceService.TestService(serviceselect.getNom())).isEmpty()) {
            // Agence agence=serviceAgence.findAgenceByUsername(name);
            int cpt = 0;
            Agence a = new Agence();
            List<Agence> agence = serviceAgence.findAll();
            for (Agence agence1 : agence) {
                if (agence1.getCode().equals(codeAgence) == true) {
                    cpt++;
                    a = agence1;
                }
            }
            if (cpt == 1) {
                serviceService.create(serviceselect, a);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "service cree avec succes", ""));


            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "code Agence invalide", ""));


            }
            //return "SaveService";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "le service " + serviceselect.getNom() + " existe deja", ""));

        }
    }

    public void updateService() {
        Service s = serviceService.findById(serviceselect.getId());
        Agence agence = serviceAgence.findById(s.getAgence().getId());
        serviceselect.setAgence(agence);
        serviceService.update(serviceselect);
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public Service getServiceselect() {
        return serviceselect;
    }

    public void setServiceselect(Service serviceselect) {
        this.serviceselect = serviceselect;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public List<Service> getServices() {
        //serviceselect=new Service("dxfdxf", "cfcgbfcgbf");
        // services.add(serviceselect);
        // String codeAgence = serviceAgence.findAgenceByUsername(name).getCode();
        return services = serviceService.findAll();

    }

    public void setServices(List<Service> services) {
        this.services = services;
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

    public ServiceAgence getServiceAgence() {
        return serviceAgence;
    }

    public void setServiceAgence(ServiceAgence serviceAgence) {
        this.serviceAgence = serviceAgence;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }
}
