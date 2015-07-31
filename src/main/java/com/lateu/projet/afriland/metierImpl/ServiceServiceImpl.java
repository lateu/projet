/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Servicedao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.metier.ServiceService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private Servicedao servicedao;

    @Override
    public void create(Service c,Agence a) {
        try {
            c.setAgence(a);
            servicedao.create(c);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Servicedao getServicedao() {
        return servicedao;
    }

    public void setServicedao(Servicedao servicedao) {
        this.servicedao = servicedao;
    }

    @Override
    public List<Service> findAll() {
        try {
            return servicedao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Service findByNomService(String nom,String codeAgence) {
        return servicedao.findbyNom(nom,codeAgence);
    }

    @Override
    public void update(Service c) {
        try {
            servicedao.update(c);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Service> TestService(String s,String codeAgence) {
        return servicedao.TestService(s, codeAgence);
    }

    @Override
    public List<Service> FindServiceByagence(String s) {
   return servicedao.findServiceByAgence(s);
    
    }

    @Override
    public Service findById(Long id) {
        try {
            return servicedao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void DeleteService(Service c) {
        try {
            servicedao.delete(c);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
