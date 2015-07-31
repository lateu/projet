/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.Agence;

/**
 *
 * @author lateu
 */
public interface Agencedao  extends IDao<Agence, Long>{
    public Agence findByCode(String code);
    public Agence findByUsername(String username);
        public Agence findByUserId(Long userId);
}
