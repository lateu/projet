/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.Servicedao;
import com.lateu.projet.afriland.entities.Service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class ServicedaoImpl extends GenericDao<Service, Long> implements Servicedao {

    @Override
    public Service findbyNom(String s) {
        try {
            return (Service) getManager().createNamedQuery("Service.findByNom")
                    .setParameter("nom", s)
                    .getSingleResult();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServicedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Service> TestService(String s) {
        try {
            return  getManager().createNamedQuery("Service.findByNom")
                             .setParameter("nom", s)
                             .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServicedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Service> findServiceByAgence(String agence) {
        try {
            return  getManager().createNamedQuery("Service.findByAgence")
                                      .setParameter("codeAgence", agence)
                                      .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServicedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
