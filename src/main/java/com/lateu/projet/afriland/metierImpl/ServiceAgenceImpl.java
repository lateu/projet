/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Agencedao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.metier.ServiceAgence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceAgenceImpl implements ServiceAgence {

    private Agencedao agencedao;

    @Override
    public void create(Agence a) {
        try {
            agencedao.create(a);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceAgenceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Agencedao getAgencedao() {
        return agencedao;
    }

    public void setAgencedao(Agencedao agencedao) {
        this.agencedao = agencedao;
    }

    @Override
    public List<Agence> findAll() {
        try {
            return agencedao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceAgenceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Agence findByCode(String codeAgence) {
        return agencedao.findByCode(codeAgence);
    }

    @Override
    public Agence findAgenceByUsername(String username) {
        return agencedao.findByUsername(username);
    }

    @Override
    public Agence findById(Long id) {
        try {
            return agencedao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceAgenceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
