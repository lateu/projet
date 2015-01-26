/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;
import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.DossierCreditdao;
import com.lateu.projet.afriland.entities.DossierCredit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */

public class DossierCreditdaoImpl extends GenericDao<DossierCredit, Long> implements DossierCreditdao{

    @Override
    public List<DossierCredit> findForEnvoi() {
        try {
            return getManager().createNamedQuery("findForEnvoi")
                                             .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierCreditdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }

    @Override
    public List<DossierCredit> findCreditForArchive() {
        try {
            return getManager().createNamedQuery("findCreditForArchives")
                                               .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierCreditdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }

    @Override
    public List<DossierCredit> findForAllCredit() {
        try {
            return getManager().createNamedQuery("findAllCredit")
                                              .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierCreditdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }

    @Override
    public List<DossierCredit> rappel() {
        try {
            return getManager().createNamedQuery("rappel")
                                                .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(DossierCreditdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      }
   
}
