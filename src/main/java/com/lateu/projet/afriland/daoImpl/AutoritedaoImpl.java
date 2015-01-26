/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.Autoritedao;
import com.lateu.projet.afriland.entities.UserAutority;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class AutoritedaoImpl extends GenericDao<UserAutority, Long> implements Autoritedao {

    @Override
    public UserAutority findByUserId(Long idUser) {
        try {
            return  (UserAutority) getManager().createNamedQuery("findbyByUserId")
                                                      .setParameter("id", idUser)
                                                      .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(AutoritedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    
}
