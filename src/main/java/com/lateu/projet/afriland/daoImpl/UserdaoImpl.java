/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.Userdao;
import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.entities.Utilisateur;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class UserdaoImpl extends GenericDao<Utilisateur, Long> implements Userdao {

    @Override
    public Utilisateur findbyUsername(String username) {
        try {
            return (Utilisateur) getManager().createNamedQuery("findbyUsername")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(UserdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            //return null;  
        }
        return null;

    }

    @Override
    public UserAutority findbyStatut(String username) {
        try {
            return (UserAutority) getManager().createNamedQuery("redirection")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(UserdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Utilisateur>  findMe(String username, String password) {
        try {
            return getManager().createNamedQuery("findMe")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(UserdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public List<Utilisateur> findUserByService(String service) {
        try {
            return getManager().createNamedQuery("findUserByService")
                            .setParameter("nom", service)
                            .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(UserdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Utilisateur> TestUsername(String username) {
        try {
            return  getManager().createNamedQuery("findbyUsername")
                             .setParameter("username",username)
                             .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(UserdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
                    
    
    }

    @Override
    public List<Utilisateur> findUserByAgence(String agence) {
        try {
            return  getManager().createNamedQuery("findbyUseByAgence")
                                .setParameter("codeAgence", agence)
                                .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(UserdaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
