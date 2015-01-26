/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface Userdao extends IDao<Utilisateur, Long> {

    public Utilisateur findbyUsername(String username);

    public List<Utilisateur> findMe(String username, String password);

    
    public List<Utilisateur> findUserByAgence(String agence);
    
    public UserAutority findbyStatut(String username);
    
    public List<Utilisateur> findUserByService(String service);
    
    public List<Utilisateur> TestUsername(String username);
    
}
