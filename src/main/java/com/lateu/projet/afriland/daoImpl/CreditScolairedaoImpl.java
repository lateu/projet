/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.CreditScolairedao;
import com.lateu.projet.afriland.entities.CreditScolaire;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class CreditScolairedaoImpl extends GenericDao<CreditScolaire, Long> implements CreditScolairedao{

    @Override
    public CreditScolaire findByNameCode(String s1,String s2) {
        try {
            return  (CreditScolaire) getManager().createNamedQuery("findbycode")
                            .setParameter("code", s1)
                            .setParameter("nom", s2)
                            .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(CreditScolairedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<CreditScolaire> findByAgence(String code) {
        try {
            return   getManager().createNamedQuery("findByAgence")
                                     .setParameter("codeAgence", code)
                                     .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(CreditScolairedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
