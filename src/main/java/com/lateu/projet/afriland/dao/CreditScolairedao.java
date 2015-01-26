/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.CreditScolaire;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface CreditScolairedao extends IDao<CreditScolaire, Long>{
    public CreditScolaire findByNameCode(String s1,String s2);
    List<CreditScolaire> findByAgence(String code);
   
}
