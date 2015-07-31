/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Agencedao;
import com.lateu.projet.afriland.dao.Archivagedao;
import com.lateu.projet.afriland.dao.Controldao;
import com.lateu.projet.afriland.dao.Dossierdao;
import com.lateu.projet.afriland.dao.Userdao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.Control;
import com.lateu.projet.afriland.entities.DossierCreationCompte;
import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceControl;
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
public class ServiceControlImpl implements ServiceControl {

    private Dossierdao dossierdao;
    private Userdao userdao;
    private Controldao controldao;
    private Archivagedao archivagedao;
    private Agencedao agencedao;

    public Dossierdao getDossierdao() {
        return dossierdao;
    }

    public void setDossierdao(Dossierdao dossierdao) {
        this.dossierdao = dossierdao;
    }

    public Userdao getUserdao() {
        return userdao;
    }

    public void setUserdao(Userdao userdao) {
        this.userdao = userdao;
    }

    public Controldao getControldao() {
        return controldao;
    }

    public void setControldao(Controldao controldao) {
        this.controldao = controldao;
    }

    public Archivagedao getArchivagedao() {
        return archivagedao;
    }

    public void setArchivagedao(Archivagedao archivagedao) {
        this.archivagedao = archivagedao;
    }

    @Override
    public void Savecontrole(Control cn, String user, Long idDoc) {
        try {
            DossierCreationCompte d = dossierdao.findById(idDoc);
            Archivage archivage = new Archivage();
            Utilisateur us = userdao.findbyUsername(user);
            UserAutority ut = userdao.findbyStatut(user);
            cn.setDossier(d);
            cn.setUtilisateur(us);
            cn.setDateControl(new Date());
            cn.setObservation("ok");
            if (ut.getAutorite().equals("ROLE_CA")) {
                d.setPas2("ok");
                d.setPas3("En cours");
                controldao.create(cn);
                dossierdao.update(d);
            } else if (ut.getAutorite().equals("ROLE_JR")) {
                d.setPas3("ok");
                d.setPas4("En cours");
                controldao.create(cn);
                dossierdao.update(d);
            } else if (ut.getAutorite().equals("ROLE_INF")) {
                d.setPas4("ok");
                d.setPas5("En cours");
                controldao.create(cn);
                dossierdao.update(d);
            } else if (ut.getAutorite().equals("ROLE_AR")) {
                d.setPas5("ok");
                controldao.create(cn);
                dossierdao.update(d);
                archivage.setClient(d.getClient());
                archivage.setCompte(d.getCompte());
                archivage.setPosition("present");
                archivage.setTypeDocument("Dossier-Compte");
                Agence a = agencedao.findByCode(d.getAgence().getCode());
                archivage.setAgence(a);
                archivagedao.create(archivage);
                System.out.println("==je suis l'archiviste svp");
            }

        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceControlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public Agencedao getAgencedao() {
        return agencedao;
    }

    public void setAgencedao(Agencedao agencedao) {
        this.agencedao = agencedao;
    }

    @Override
    public List<Control> findControl() {
        try {
            return controldao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceControlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
