/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.DossierCreationCompte;
import com.lateu.projet.afriland.entities.DossierCredit;
import com.lateu.projet.afriland.metier.ServiceAgence;
import com.lateu.projet.afriland.metier.ServiceDossierCredit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
public class DossierCreditBean {

    @ManagedProperty(value = "#{ServiceDossierCredit}")
    private ServiceDossierCredit serviceDossierCredit;
    @ManagedProperty(value = "#{ServiceAgence}")
    private ServiceAgence serviceAgence;
    private DossierCredit creditSelect = new DossierCredit();
    public List<DossierCredit> dossierCreditsForEnvoi;
    public List<DossierCredit> dossierCreditsForArchive;
    public List<DossierCredit> dossierCredits;
    /**
     * nobre de dossier de credit disponible pour l'archivage
     */
    private int totalcredit;

    public DossierCreditBean() {
    }

    public ServiceDossierCredit getServiceDossierCredit() {
        return serviceDossierCredit;
    }

    public void setServiceDossierCredit(ServiceDossierCredit serviceDossierCredit) {
        this.serviceDossierCredit = serviceDossierCredit;
    }

    public ServiceAgence getServiceAgence() {
        return serviceAgence;
    }

    public void setServiceAgence(ServiceAgence serviceAgence) {
        this.serviceAgence = serviceAgence;
    }

    public void chargerDossierCredit(FileUploadEvent event) throws DataAccessException, FileNotFoundException, IOException, ParseException {
        InputStream file = event.getFile().getInputstream();

        Scanner scanner = new Scanner(file);
        // Agence a = new Agence();
        int cpt = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            creditSelect = new DossierCredit();
            int deb = 0, fin = 0;
            String agence = null;
            String compte = null;
            String NoDoc = null;

            String dateMise = null;
            String dateFirst = null;
            String dateLast = null;
            String nom = null;
            int l = line.length();
            for (int i = 0; i < l; i++) {

                agence = line.substring(0, 5);
                NoDoc = line.substring(6, 12);
                compte = line.substring(13, 19);
                nom = line.substring(21, l - 34);
                dateMise = line.substring(l - 33, l - 23);
                dateFirst = line.substring(l - 22, l - 12);
                dateLast = line.substring(l - 11, l - 1);
            }

            creditSelect.setClient(nom);
            creditSelect.setCompte(compte);
            creditSelect.setDateFirstEcheance(new SimpleDateFormat("dd/MM/yyyy").parse(dateFirst));
            creditSelect.setDateLastEcheance(new SimpleDateFormat("dd/MM/yyyy").parse(dateLast));
            creditSelect.setDateMiseEnPlace(new SimpleDateFormat("dd/MM/yyyy").parse(dateMise));
            creditSelect.setPas1("en cours");
            creditSelect.setPas2("0");
            creditSelect.setNoPret(NoDoc);
            serviceDossierCredit.createDossierCredit(creditSelect, serviceAgence.findByCode(agence));
            cpt++;
            System.out.println("agence= " + agence + " NoDoc = " + NoDoc + " compte=" + compte + "  nom= " + nom + " dateMise=" + dateMise + " dateFirst=" + dateFirst + " dateLast=" + dateLast);
            //faites ici votre traitement
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " " + cpt + " lignes enregistrées", ""));

        scanner.close();

    }

    public DossierCredit getCreditSelect() {
        return creditSelect;
    }

    public void setCreditSelect(DossierCredit creditSelect) {
        this.creditSelect = creditSelect;
    }

    public List<DossierCredit> getDossierCreditsForEnvoi() {
        return dossierCreditsForEnvoi=serviceDossierCredit.EnvoiArchive() ;
    }

    public void setDossierCreditsForEnvoi(List<DossierCredit> dossierCreditsForEnvoi) {
        this.dossierCreditsForEnvoi = dossierCreditsForEnvoi;
    }

    public List<DossierCredit> getDossierCreditsForArchive() {
        return dossierCreditsForArchive=serviceDossierCredit.CreditsForArchive();
    }

    public void setDossierCreditsForArchive(List<DossierCredit> dossierCreditsForArchive) {
        this.dossierCreditsForArchive = dossierCreditsForArchive;
    }

    public List<DossierCredit> getDossierCredits() {
        return dossierCredits=serviceDossierCredit.findForAllCredit();
    }

    public void setDossierCredits(List<DossierCredit> dossierCredits) {
        this.dossierCredits = dossierCredits;
    }

    public int getTotalcredit() {
        return totalcredit=serviceDossierCredit.EnvoiArchive().size();
    }

    public void setTotalcredit(int totalcredit) {
        this.totalcredit = totalcredit;
    }
     public void avancerDossier() {
        List<DossierCredit> l = serviceDossierCredit.EnvoiArchive();
        for (DossierCredit d : l) {

            d.setPas2("En cours");
            d.setPas1("ok");
            serviceDossierCredit.AvencerDossierCredit(d);
        }

    }
    
     public void archiverDossierCredit(){
     serviceDossierCredit.ArchiverDossierCredit(creditSelect, serviceAgence.findById(creditSelect.getAgence().getId()));
       //  System.out.println("==="+creditSelect.getId());
     }
     
     public void rappeller(){
        
       List<DossierCredit> l = serviceDossierCredit.rappel();
       
       int cpt=0;
       Long id=0L;
         for (DossierCredit dossierCredit : l) {
             id=dossierCredit.getId();
             System.out.println("=================...rappel......====================="+id);
             serviceDossierCredit.deleteDossierCredit(id);
             cpt++;
         }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " " + cpt + "  annulations", ""));

     }
    
}
