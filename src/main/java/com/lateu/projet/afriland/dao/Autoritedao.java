/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.UserAutority;

/**
 *
 * @author lateu
 */
public interface Autoritedao  extends IDao<UserAutority, Long>{
   public  UserAutority findByUserId(Long idUser);
}
