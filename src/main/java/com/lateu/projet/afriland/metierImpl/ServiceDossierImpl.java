/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Agencedao;
import com.lateu.projet.afriland.dao.Anomaliedao;
import com.lateu.projet.afriland.dao.Archivagedao;
import com.lateu.projet.afriland.dao.Dossierdao;
import com.lateu.projet.afriland.dao.Userdao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Anomalie;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.DossierCreationCompte;
import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.metier.ServiceDossier;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceDossierImpl implements ServiceDossier {

    private Dossierdao dossierdao;
    private Anomaliedao anomaliedao;
    private Userdao userdao;
    private Archivagedao archivagedao;
    private Agencedao agencedao;

    public Dossierdao getDossierdao() {
        return dossierdao;
    }

    public void setDossierdao(Dossierdao dossierdao) {
        this.dossierdao = dossierdao;
    }

    public Anomaliedao getAnomaliedao() {
        return anomaliedao;
    }

    public void setAnomaliedao(Anomaliedao anomaliedao) {
        this.anomaliedao = anomaliedao;
    }

    @Override
    public void create(DossierCreationCompte d, Agence a) {
        try {
            d.setAgence(a);
            d.setPas5("En cours");
            d.setPas1("0");
            d.setPas2("0");
            d.setPas3("0");
            d.setPas4("0");
            dossierdao.create(d);
            //  System.out.println("=ok");

        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Valider(DossierCreationCompte d, String indice) throws DataAccessException {
        UserAutority r = userdao.findbyStatut(indice);
        // Utilisateur u=userdao.findbyUsername(indice);
        Archivage archivage = new Archivage();

        if ((r.getAutorite()).equals("ROLE_CA") == true) {
            d.setPas1("ok");
            dossierdao.update(d);
        }

        if ((r.getAutorite()).equals("ROLE_JR") == true) {
            d.setPas2("ok");
            dossierdao.update(d);
        }

        if ((r.getAutorite()).equals("ROLE_INF") == true) {
            d.setPas3("ok");
            dossierdao.update(d);
        }

        if ((r.getAutorite()).equals("ROLE_AR") == true) {
            d.setPas4("ok");
            dossierdao.update(d);
            archivage.setClient(d.getClient());
            archivage.setCompte(d.getCompte());
            archivage.setPosition("present");
            archivage.setTypeDocument("Dossier-Compte");
            archivagedao.create(archivage);
            System.out.println("==je suis l'archiviste svp");
        }

        //      dossierdao.update(d);
    }

    public Userdao getUserdao() {
        return userdao;
    }

    public void setUserdao(Userdao userdao) {
        this.userdao = userdao;
    }

    @Override
    public List<DossierCreationCompte> findAll() {
        
            return dossierdao.findForALL();
      
    }

    @Override
    public List<DossierCreationCompte> findForCA() {
        return dossierdao.findForCA();
    }

    @Override
    public List<DossierCreationCompte> findForCJ() {
        return dossierdao.findForJR();
    }

    @Override
    public List<DossierCreationCompte> findForINF() {
        return dossierdao.findForINF();

    }

    @Override
    public List<DossierCreationCompte> findAR() {
        return dossierdao.findForAR();
    }

    public Archivagedao getArchivagedao() {
        return archivagedao;
    }

    public void setArchivagedao(Archivagedao archivagedao) {
        this.archivagedao = archivagedao;
    }

//    @Override
//    public void createAnomalie(Anomalie an) {
//        try {
//            anomaliedao.create(an);
//        } catch (DataAccessException ex) {
//            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @Override
    public List<Anomalie> findAllAnomalie() {
        try {
            return anomaliedao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void createAnomalie(Anomalie a, Long idDossier) {
        try {
            a.setDossier(dossierdao.findById(idDossier));
            anomaliedao.create(a);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void corrigerAnomalie(Anomalie an, String userName) {
        try {
            Anomalie a = anomaliedao.findById(an.getId());
            a.setPersonneCor(userName);
            a.setDateCo(new Date());
            a.setSolution(an.getSolution());
            // System.out.println("----------"+a);
            anomaliedao.update(a);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Anomalie> findByAnomalieAgence(String codeAgence) {

        return anomaliedao.findAnomalieByAgence(codeAgence);
    }

    @Override
    public List<DossierCreationCompte> findByDossierPeriode(Date debut, Date fin) {
        return dossierdao.findByPeriode(debut, fin);
    }

    @Override
    public List<Anomalie> findAnomalie(String correcteur) {
        return anomaliedao.findAnomalie(correcteur);
    }

    public Agencedao getAgencedao() {
        return agencedao;
    }

    public void setAgencedao(Agencedao agencedao) {
        this.agencedao = agencedao;
    }

    @Override
    public List<DossierCreationCompte> findForAccueil() {
        return dossierdao.findForAccueil();
    }

    @Override
    public void Circuler(DossierCreationCompte d) {
        try {    
            d.setDateMiseEnCirculation(new Date());
            dossierdao.update(d);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(DossierCreationCompte d) {
        try {
            dossierdao.update(d);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletedossierJuridiqu(Long id) {
        try {
            dossierdao.delete(dossierdao.findById(id));
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
