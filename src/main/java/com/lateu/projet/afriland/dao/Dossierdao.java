/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.Control;
import com.lateu.projet.afriland.entities.DossierCreationCompte;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface Dossierdao extends IDao<DossierCreationCompte, Long> {

    public List<DossierCreationCompte> findForAccueil();

    public List<DossierCreationCompte> findForCA(String codeAgence);

    public List<DossierCreationCompte> findForALL();

    public List<DossierCreationCompte> findForJR(String codeAgence);

    public List<DossierCreationCompte> findForINF(String codeAgence);

    public List<DossierCreationCompte> findForAR(String codeAgence);

    public List<DossierCreationCompte> findByPeriode(Date debut, Date fin);

    public List<Control> findAction(String compte);
}
