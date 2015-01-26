/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.CreditScolaire;
import com.lateu.projet.afriland.projection.projectionCredit;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceCreditScolaire {

    public void create(CreditScolaire cs, String usr, Agence a) throws DataAccessException;

    // public Long LastIndex(List<CreditScolaire> creditScolaires);
    public void upDateCreditS(CreditScolaire cs) throws DataAccessException;

    public List<CreditScolaire> findAll() throws DataAccessException;

    public List<CreditScolaire> findByAgence(String code) throws DataAccessException;

    public CreditScolaire findClient(String a, String b) throws DataAccessException;

    public CreditScolaire findById(Long id) throws DataAccessException;

    public projectionCredit rapport(List<CreditScolaire> liste);

    public void DeleteCredit(Long id);

    public int DocNumber(List<CreditScolaire> liste) throws DataAccessException;

    public int TraiterAtemps(List<CreditScolaire> liste) throws DataAccessException;

    public int VolumeDemande(List<CreditScolaire> liste) throws DataAccessException;

    public int Assurence(List<CreditScolaire> liste) throws DataAccessException;

    public int VolumeAccorde(List<CreditScolaire> liste) throws DataAccessException;

    public int ReclamationNumber(List<CreditScolaire> liste) throws DataAccessException;

    public int nbreDocValider(List<CreditScolaire> liste) throws DataAccessException;

    public int nbreDocEncours(List<CreditScolaire> liste) throws DataAccessException;
}
