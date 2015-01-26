/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.lateu.projet.afriland.entities.Poubelle;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceAgence;
import com.lateu.projet.afriland.metier.ServiceService;
import com.lateu.projet.afriland.metier.ServiceUtilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
//@ViewScoped
public class utilisateurbean implements Serializable {

    /**
     * Creates a new instance of utilisateurbean
     */
    @ManagedProperty(value = "#{ServiceUtilisateur}")
    private ServiceUtilisateur serviceUtilisateur;
    @ManagedProperty(value = "#{ServiceService}")
    private ServiceService serviceService;
    @ManagedProperty(value = "#{ServiceAgence}")
    private ServiceAgence serviceAgence;
    private Utilisateur utilisateurSelect = new Utilisateur();
    private String role;
    private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    private List<Poubelle> poubelles;
    private List<Service> services = new ArrayList<Service>();
    private String NomService;
    private String motifSuppression;
    private SelectItem[] listeServiceSelect;
    private String pass;
    private String pass1;
    private String pass2;
    private String userToInit;
    private String codeAgence;
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername();
    //String name = "lateu";

    public utilisateurbean() {
    }

    public void creerUser() {
        int cpt = 0;
        if (name != null) {
            if ((serviceUtilisateur.TestUsername(utilisateurSelect.getUsername()).isEmpty())) {
                utilisateurSelect.setAccountState(1);
                utilisateurSelect.setPassword("xxxx");
                
                if(NomService.equals("choisir")==true){
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Service Invalide", ""));             
                    }else{
                    
                services = serviceService.FindServiceByagence(codeAgence);
                for (Service s : services) {
                    if (s.getNom().equals(NomService) == true) {
                        cpt++;

                    }
                }
                /**
                 * on verifie que le service existe reellement dans la dite agence
                 */
                if (cpt == 1) {
                    serviceUtilisateur.create(utilisateurSelect, role, NomService);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "utilisateur " + utilisateurSelect.getUsername() + " ajouter avec succés", ""));
                 
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Code agence Invalide", ""));

                }

                }
                
                
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " nom d'utilisateur non disponible", ""));

            
            }          

    }
    }
    
    
    public void InitPassword() {
        serviceUtilisateur.InitPassord(userToInit);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "mot de passe reinitialisé ", ""));


    }

    public void updateUser() {
        if (name != null) {

            if (role.equals("choisir")) {
                //utilisateurSelect.s
                UserAutority u = serviceUtilisateur.findByUserId(utilisateurSelect.getId());
            } else {

                serviceUtilisateur.update(utilisateurSelect, role, NomService);
            }

            System.out.println("==================gggggggggggggggggggggggggggggggggggggggg=");
//
        }
    }

    public void localize(ActionEvent actionEvent) {
        List<Utilisateur> l;
        l = serviceUtilisateur.findMe(name, pass);
        if (l.isEmpty()) {
            //   System.out.println("=====le couple ne correspond pas");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, " mot de passe actuel incorrect", ""));
        } else if (pass1.equals(pass2) == true) {
            Utilisateur u = new Utilisateur();

            for (Utilisateur utilisateur : l) {
                if (utilisateur != null) {
                    u = utilisateur;
                }
            }

            if (pass1.length() != 0) {
                u.setPassword(pass1);
                serviceUtilisateur.ChangePassword(u);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "votre mot de passe à été changé", ""));

            } else {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "nouveau mot vide non autorisé", ""));

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "les deux nouveaux pass sont different", ""));

        }

    }

    public void delete() {

        if (name != null) {
            if ("".equals(motifSuppression)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, " le motif de suppression est obligatoire", ""));
            } else {
                String s = "l\'utilisateur " + utilisateurSelect.getUsername() + " à été supprimé parceque " + motifSuppression;
                Poubelle pb = new Poubelle();
                //   pb.;
                pb.setMotif(s);
//                serviceUtilisateur.creerPoubelle(pb,serviceUtilisateur.findByUsername(name));
//                serviceUtilisateur.deleteMonRole(name);
//                serviceUtilisateur.delete(utilisateurSelect.getId());
                System.out.println("==========="+utilisateurSelect.getId());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "dossier supprimé avec succés", ""));
            }
        }
    }

    public List<Poubelle> getPoubelles() {
        return poubelles = serviceUtilisateur.findAllPoubelle();
    }

    public void setPoubelles(List<Poubelle> poubelles) {
        this.poubelles = poubelles;
    }

    public ServiceUtilisateur getServiceUtilisateur() {
        return serviceUtilisateur;
    }

    public void setServiceUtilisateur(ServiceUtilisateur serviceUtilisateur) {
        this.serviceUtilisateur = serviceUtilisateur;
    }

    public Utilisateur getUtilisateurSelect() {
        return utilisateurSelect;
    }

    public void setUtilisateurSelect(Utilisateur utilisateurSelect) {
        this.utilisateurSelect = utilisateurSelect;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Utilisateur> getUtilisateurs() {
        if (name != null) {
            // codeAgence=serviceAgence.findAgenceByUsername(name).getCode();
            return utilisateurs = serviceUtilisateur.findAll();
        }
        return null;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public String getMotifSuppression() {
        return motifSuppression;
    }

    public void setMotifSuppression(String motifSuppression) {
        this.motifSuppression = motifSuppression;
    }

    public String getNomService() {
        return NomService;
    }

    public void setNomService(String NomService) {
        this.NomService = NomService;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public SelectItem[] getListeServiceSelect() {
        services = serviceService.findAll();
        // services = serviceService.FindServiceByagence(codeAgence);
        // codeAgence = serviceAgence.findAgenceByUsername(name).getCode();
        listeServiceSelect = new SelectItem[services.size() + 1];
        listeServiceSelect[0] = new SelectItem("choisir");
        for (int i = 1; i < services.size() + 1; i++) {
            listeServiceSelect[i] = new SelectItem(services.get(i - 1).getNom());
        }

        return listeServiceSelect;
    }

    public void setListeServiceSelect(SelectItem[] listeServiceSelect) {
        this.listeServiceSelect = listeServiceSelect;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserToInit() {
        return userToInit;
    }

    public void setUserToInit(String userToInit) {
        this.userToInit = userToInit;
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

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public ServiceAgence getServiceAgence() {
        return serviceAgence;
    }

    public void setServiceAgence(ServiceAgence serviceAgence) {
        this.serviceAgence = serviceAgence;
    }
}
