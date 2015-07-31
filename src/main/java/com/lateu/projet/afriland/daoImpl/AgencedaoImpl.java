/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.Agencedao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Archivage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class AgencedaoImpl extends GenericDao<Agence, Long> implements Agencedao{

    @Override
    public Agence findByCode(String code) {
        try {
            return (Agence) getManager().createNamedQuery("findAgenceByCode")
                                   .setParameter("codeAgence", code)
                                   .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(AgencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Agence findByUsername(String username) {
        try {
            return (Agence) getManager().createNamedQuery("findAgenceByUsername")
                                  .setParameter("username", username)
                                  .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(AgencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
      @Override
    public Agence findByUserId(Long userId) {
        try {
            return (Agence) getManager().createNamedQuery("findAgenceByUserId")
                                  .setParameter("userId", userId)
                                  .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(AgencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
