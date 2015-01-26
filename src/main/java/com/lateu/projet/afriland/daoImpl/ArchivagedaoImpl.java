/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.Archivagedao;
import com.lateu.projet.afriland.entities.Archivage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class ArchivagedaoImpl extends GenericDao<Archivage, Long> implements Archivagedao{

    @Override
    public Archivage findByAccount(String Account) {
        try {
            return (Archivage) getManager().createNamedQuery("Archivage.findByAccount")
                            .setParameter("Account", Account)
                            .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(ArchivagedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    @Override
//    public List<CreditScolaire> Credit_S_Archiable() {
//        try {
//            return  getManager().createNamedQuery("findCredit_S_Archiable")
//                                     .getResultList();
//        } catch (DataAccessException ex) {
//            Logger.getLogger(ArchivagedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    @Override
    public Archivage findByIden(Long id) {
      
           try {
            return (Archivage) getManager().createNamedQuery("Archivage.findById")
                            .setParameter("id", id)
                            .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(ArchivagedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
