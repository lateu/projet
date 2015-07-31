/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Anomalie;
import com.lateu.projet.afriland.entities.DossierCreationCompte;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceDossier {

    public void create(DossierCreationCompte d, Agence a);

    public void deletedossierJuridiqu(Long id);

    public void createAnomalie(Anomalie a, Long idDossier);

    public void Valider(DossierCreationCompte d, String indice) throws DataAccessException;

    public List<DossierCreationCompte> findAll();

    public List<Anomalie> findAllAnomalie();

    List<Anomalie> findByAnomalieAgence(String codeAgence);

    public List<DossierCreationCompte> findForAccueil();

    public List<DossierCreationCompte> findForCA(String codeAgence);

    public List<DossierCreationCompte> findForCJ(String codeAgence);

    public List<DossierCreationCompte> findForINF(String codeAgence);

    public List<DossierCreationCompte> findAR(String codeAgence);

    //public void createAnomalie(Anomalie an);
    public void corrigerAnomalie(Anomalie an, String userName);

    public List<DossierCreationCompte> findByDossierPeriode(Date debut, Date fin);

    public List<Anomalie> findAnomalie(String correcteur);

    public void Circuler(DossierCreationCompte d);

    public void Update(DossierCreationCompte d);
}
