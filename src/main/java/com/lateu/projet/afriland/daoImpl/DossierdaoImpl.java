/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.Dossierdao;
import com.lateu.projet.afriland.entities.Control;
import com.lateu.projet.afriland.entities.DossierCreationCompte;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class DossierdaoImpl extends GenericDao<DossierCreationCompte, Long> implements Dossierdao {

    @Override
    public List<DossierCreationCompte> findForCA(String codeAgence) {
        try {

            return getManager().createNamedQuery("findForCA")
                    .setParameter("codeAgence", codeAgence)
                    .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<DossierCreationCompte> findForJR(String codeAgence) {

        try {

            return getManager().createNamedQuery("findForJR")
                   .setParameter("codeAgence", codeAgence)
                    .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<DossierCreationCompte> findForINF(String codeAgence) {


        try {

            return getManager().createNamedQuery("findForINF")
                    .setParameter("codeAgence", codeAgence)
                    .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<DossierCreationCompte> findForAR(String codeAgence) {

        try {

            return getManager().createNamedQuery("findForAR")
                    .setParameter("codeAgence", codeAgence)
                    .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<DossierCreationCompte> findByPeriode(Date debut, Date fin) {
        try {
            return getManager().createNamedQuery("findByPeriode")
                    .setParameter("debut", debut)
                    .setParameter("fin", fin)
                    .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<Control> findAction(String compte) {
        try {
            return getManager().createNamedQuery("findAction")
                           .setParameter("compte", compte)
                           .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<DossierCreationCompte> findForAccueil() {
        try {
            return getManager().createNamedQuery("findAccueil")
                                      .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<DossierCreationCompte> findForALL() {
        try {
            return getManager().createNamedQuery("findForALL")
                                                .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
