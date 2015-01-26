/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.DossierCredit;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceDossierCredit {

    public void createDossierCredit(DossierCredit d, Agence a);

    public List<DossierCredit> EnvoiArchive();

    public List<DossierCredit> CreditsForArchive();

    public List<DossierCredit> findForAllCredit();
    
    public List<DossierCredit> rappel();
    
    public void deleteDossierCredit(Long d);

    public void AvencerDossierCredit(DossierCredit d);

    public void ArchiverDossierCredit(DossierCredit doc,Agence a);
}
