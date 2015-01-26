/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.DossierCredit;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface DossierCreditdao extends IDao<DossierCredit, Long> {

    public List<DossierCredit> findForEnvoi();
    public List<DossierCredit> findCreditForArchive();
    public List<DossierCredit> findForAllCredit();
     public List<DossierCredit> rappel();
}
