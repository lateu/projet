/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.Archivage;

/**
 *
 * @author lateu
 */
public interface Archivagedao extends IDao<Archivage, Long>{
    public Archivage findByAccount(String Account);
     public Archivage findByIden(Long id);
    //public List<CreditScolaire>  Credit_S_Archiable();
}
