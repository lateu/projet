/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.DossierCredit;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceArchivage {

    public void create(Archivage a, Agence ag) throws DataAccessException;

    public Archivage findById(Long Id);

    public List<Archivage> findAll();

    public Archivage findbyAcount(String s);

    public void UpdateArchive(Archivage a);

 
    // public List<CreditScolaire>  Credit_S_Archiable();
}
