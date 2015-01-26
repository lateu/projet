/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Archivagedao;
import com.lateu.projet.afriland.dao.DossierCreditdao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.DossierCredit;
import com.lateu.projet.afriland.metier.ServiceDossierCredit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceDossierCreditImpl implements ServiceDossierCredit {

    private DossierCreditdao creditdao;
    private Archivagedao archivagedao;

    @Override
    public void createDossierCredit(DossierCredit d, Agence a) {
        d.setAgence(a);
        try {
            creditdao.create(d);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierCreditImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Archivagedao getArchivagedao() {
        return archivagedao;
    }

    public void setArchivagedao(Archivagedao archivagedao) {
        this.archivagedao = archivagedao;
    }

    @Override
    public List<DossierCredit> EnvoiArchive() {
        return creditdao.findForEnvoi();
    }

    @Override
    public List<DossierCredit> CreditsForArchive() {
        return creditdao.findCreditForArchive();
    }

    @Override
    public List<DossierCredit> findForAllCredit() {
        return creditdao.findForAllCredit();
    }

    public DossierCreditdao getCreditdao() {
        return creditdao;
    }

    public void setCreditdao(DossierCreditdao creditdao) {
        this.creditdao = creditdao;
    }

    @Override
    public void AvencerDossierCredit(DossierCredit d) {
        try {
            // d.setPas1("ok");
            creditdao.update(d);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierCreditImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ArchiverDossierCredit(DossierCredit doc, Agence a) {
        try {
            Archivage ar = new Archivage();
            ar.setClient(doc.getClient());
            ar.setCompte(doc.getCompte());
            ar.setPosition("present");
            ar.setTypeDocument("Dossier-Credit");
            ar.setAgence(a);
            archivagedao.create(ar);
            doc.setPas2("ok");
            creditdao.update(doc);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierCreditImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<DossierCredit> rappel() {
        return creditdao.rappel();
    }

    @Override
    public void deleteDossierCredit(Long id) {
        try {
            creditdao.delete(creditdao.findById(id));
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceDossierCreditImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
