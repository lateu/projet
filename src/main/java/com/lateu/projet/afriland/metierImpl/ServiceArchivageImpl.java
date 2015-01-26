/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Agencedao;
import com.lateu.projet.afriland.dao.Archivagedao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.DossierCredit;
import com.lateu.projet.afriland.metier.ServiceArchivage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceArchivageImpl implements ServiceArchivage {

    private Archivagedao archivagedao;
    private Agencedao agencedao;
//private Userdao userdao;

    @Override
    public void create(Archivage a, Agence ag) throws DataAccessException {
        //Utilisateur u=userdao.findbyUsername(utilisateu);

        //  a.setUtilisateur(u);
        Agence agence = agencedao.findById(ag.getId());
        a.setPosition("present");
        a.setAgence(agence);
        archivagedao.create(a);

    }

    public Archivagedao getArchivagedao() {
        return archivagedao;
    }

    public void setArchivagedao(Archivagedao archivagedao) {
        this.archivagedao = archivagedao;
    }

    @Override
    public List<Archivage> findAll() {
        try {
            return archivagedao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceArchivageImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Archivage findbyAcount(String s) {
        return archivagedao.findByAccount(s);
    }

    @Override
    public void UpdateArchive(Archivage a) {
        try {
            archivagedao.update(a);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceArchivageImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Archivage findById(Long Id) {

        return archivagedao.findByIden(Id);

    }

//    @Override
//    public List<CreditScolaire> Credit_S_Archiable() {
// return  archivagedao.Credit_S_Archiable();
//    }
//      
    public Agencedao getAgencedao() {
        return agencedao;
    }

    public void setAgencedao(Agencedao agencedao) {
        this.agencedao = agencedao;
    }

}