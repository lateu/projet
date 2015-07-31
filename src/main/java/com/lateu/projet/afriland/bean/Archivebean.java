/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.Mouvement;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceAgence;
import com.lateu.projet.afriland.metier.ServiceArchivage;
import com.lateu.projet.afriland.metier.ServiceCreditScolaire;
import com.lateu.projet.afriland.metier.ServiceMouvement;
import com.lateu.projet.afriland.metier.ServiceService;
import com.lateu.projet.afriland.metier.ServiceUtilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author lateu
 */
@ManagedBean
@ViewScoped
//@RequestScoped
public class Archivebean implements Serializable {

    @ManagedProperty(value = "#{ServiceArchivage}")
    private ServiceArchivage serviceArchivage;
    @ManagedProperty(value = "#{ServiceMouvement}")
    private ServiceMouvement serviceMouvement;
    @ManagedProperty(value = "#{ServiceService}")
    private ServiceService serviceService;
    @ManagedProperty(value = "#{ServiceUtilisateur}")
    ServiceUtilisateur serviceUtilisateur;
    @ManagedProperty(value = "#{ServiceCreditScolaire}")
    private ServiceCreditScolaire serviceCreditScolaire;
    @ManagedProperty(value = "#{ServiceAgence}")
    private ServiceAgence serviceAgence;
    private Archivage archivageselecr = new Archivage();
    public List<Archivage> archivages = new ArrayList<Archivage>();
    public List<Utilisateur> utilisateurs;
    private String username;
    private int type;
    private Mouvement mouvementSelect = new Mouvement();
    private SelectItem[] listeServiveSelect;
    private SelectItem[] listePersoneSelect;
    private String nomService;
    private String nomPersonnel;
    //  public List<CreditScolaire> creditScolaires;
    //private CreditScolaire creditScolaireSelect = new CreditScolaire();
    //private Utilisateur UserSelect = new Utilisateur();
    Long idPersonnel;
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername();

    public Archivebean() {
    }

    public String create() throws DataAccessException {
        //  System.out.println("==="+archivageselecr);
        if (name != null) {
            Agence a=serviceAgence.findAgenceByUsername(name);
            serviceArchivage.create(archivageselecr,a);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "dossier ajouté avec succés", ""));
return "createArchive";
        }
        return null;
    }

    public void sortie() {
        if (name != null) {
            Archivage ar = serviceArchivage.findById(archivageselecr.getId());
            System.out.println("===" + idPersonnel);
            Agence ag=serviceAgence.findAgenceByUsername(name);
            ar.setPosition("absent");
            //   if (archivageselecr.getPosition() == 0) {
            Service ser = serviceService.findByNomService(nomService,ag.getCode());
            Mouvement m = new Mouvement();
            m.setService(ser);
            m.setArchive(ar);
            m.setDateMovement(new Date());

            serviceArchivage.UpdateArchive(ar);
            serviceMouvement.create(archivageselecr.getCompte(), ser.getNom(), idPersonnel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sortie enregistrée", ""));

        }

    }

    public void retour() {
        if (name != null) {
            Archivage ar = serviceArchivage.findById(archivageselecr.getId());
            ar.setPosition("present");
            serviceArchivage.UpdateArchive(ar);
            List<Mouvement> mts = serviceMouvement.findAll();

            for (Mouvement mouvement : mts) {
                if (mouvement.getArchive().getCompte().equals(archivageselecr.getCompte()) == true) {
                    serviceMouvement.Delete(mouvement.getId());

                }
            }
        }   // System.out.println("==="+archivageselecr);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "retour enregistré", ""));
        
    }

    public ServiceArchivage getServiceArchivage() {
        return serviceArchivage;
    }

    public void setServiceArchivage(ServiceArchivage serviceArchivage) {
        this.serviceArchivage = serviceArchivage;
    }

    public Archivage getArchivageselecr() {
        return archivageselecr;
    }

    public void setArchivageselecr(Archivage archivageselecr) {
        this.archivageselecr = archivageselecr;
    }

    public List<Archivage> getArchivages() {
        if (name != null) {
            return archivages = serviceArchivage.findAll();
        }
        return null;
    }

    public void setArchivages(List<Archivage> archivages) {
        this.archivages = archivages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ServiceMouvement getServiceMouvement() {
        return serviceMouvement;
    }

    public void setServiceMouvement(ServiceMouvement serviceMouvement) {
        this.serviceMouvement = serviceMouvement;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public Mouvement getMouvementSelect() {
        return mouvementSelect;
    }

    public void setMouvementSelect(Mouvement mouvementSelect) {
        this.mouvementSelect = mouvementSelect;
    }

    public ServiceCreditScolaire getServiceCreditScolaire() {
        return serviceCreditScolaire;
    }

    public void setServiceCreditScolaire(ServiceCreditScolaire serviceCreditScolaire) {
        this.serviceCreditScolaire = serviceCreditScolaire;
    }

    public SelectItem[] getListeServiveSelect() {
       // List<Service> services = serviceService.findAll();
        String codeAgence=serviceAgence.findAgenceByUsername(name).getCode();
        List<Service> services=serviceService.FindServiceByagence(codeAgence);
        listeServiveSelect = new SelectItem[services.size() + 1];
        listeServiveSelect[0] = new SelectItem("choisir");
        for (int i = 1; i < services.size() + 1; i++) {
            listeServiveSelect[i] = new SelectItem(services.get(i - 1).getNom());
        }
        return listeServiveSelect;
    }

    public void setListeServiveSelect(SelectItem[] listeServiveSelect) {
        this.listeServiveSelect = listeServiveSelect;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ServiceUtilisateur getServiceUtilisateur() {
        return serviceUtilisateur;
    }

    public void setServiceUtilisateur(ServiceUtilisateur serviceUtilisateur) {
        this.serviceUtilisateur = serviceUtilisateur;
    }

    public void handleNomServiceChange() {
        if (nomService != null && !nomService.equals("")) {
            utilisateurs = serviceUtilisateur.findUserByService(nomService);
        } else {
            utilisateurs = new ArrayList<Utilisateur>();
        }
    }

    public SelectItem[] getListePersoneSelect() {
        // List<Service> services = serviceService.findAll();
        if (name != null) {
            utilisateurs = serviceUtilisateur.findUserByService(nomService);
            listePersoneSelect = new SelectItem[utilisateurs.size() + 1];
            listePersoneSelect[0] = new SelectItem("Select Personnel");
            for (int i = 1; i < utilisateurs.size() + 1; i++) {
                listePersoneSelect[i] = new SelectItem(utilisateurs.get(i - 1).getNom());
            }
            return listePersoneSelect;
        }
        return null;
    }

    public void setListePersoneSelect(SelectItem[] listePersoneSelect) {
        this.listePersoneSelect = listePersoneSelect;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Long getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Long idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

//    public List<CreditScolaire> getCreditScolaires() {
//        return creditScolaires = serviceArchivage.Credit_S_Archiable();
//    }
//
//    public void setCreditScolaires(List<CreditScolaire> creditScolaires) {
//        this.creditScolaires = creditScolaires;
//    }
//    public CreditScolaire getCreditScolaireSelect() {
//        return creditScolaireSelect;
//    }
//
//    public void setCreditScolaireSelect(CreditScolaire creditScolaireSelect) {
//        this.creditScolaireSelect = creditScolaireSelect;
//    }
    //  public void ArchiverCreditS() throws DataAccessException {
    //     if (name != null) {
    //   String cl = creditScolaireSelect.getClient();
    //  String c = creditScolaireSelect.getCompte();
//            archivageselecr.setClient(cl);
//            archivageselecr.setCompte(c);
//            archivageselecr.setPosition(1);
//            archivageselecr.setTypeDocument("credit-Scolaire");
//            serviceArchivage.create(archivageselecr);
//            creditScolaireSelect.setArchiver(1);
//            serviceCreditScolaire.upDateCreditS(creditScolaireSelect);
    //         System.out.println("=====================je suis là=============" + c + " nom=  " + cl);
    //       System.out.println("=====================je suis là============="+creditScolaireSelect);
    //      }
    // }
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
    
    
    
    
    
}
