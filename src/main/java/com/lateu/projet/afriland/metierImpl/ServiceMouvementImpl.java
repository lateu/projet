/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Archivagedao;
import com.lateu.projet.afriland.dao.Mouvementdao;
import com.lateu.projet.afriland.dao.Servicedao;
import com.lateu.projet.afriland.dao.Userdao;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.Mouvement;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceMouvement;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceMouvementImpl implements ServiceMouvement {

    private Mouvementdao mouvementdao;
    private Archivagedao archivagedao;
    private Servicedao servicedao;
    private Userdao userdao;

    @Override
    public void create(String archive, String service, Long idUser) {
        try {
            Service s = new Service();
         
            Mouvement m = new Mouvement();

            s = servicedao.findbyNom(service);
           Archivage a  = archivagedao.findByAccount(archive);
            Utilisateur U = userdao.findById(idUser);
            m.setArchive(a);
            m.setService(s);
            m.setUtilisateur(U);
            m.setDateMovement(new Date());
            mouvementdao.create(m);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceMouvementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mouvementdao getMouvementdao() {
        return mouvementdao;
    }

    public void setMouvementdao(Mouvementdao mouvementdao) {
        this.mouvementdao = mouvementdao;
    }

    public Archivagedao getArchivagedao() {
        return archivagedao;
    }

    public void setArchivagedao(Archivagedao archivagedao) {
        this.archivagedao = archivagedao;
    }

    public Servicedao getServicedao() {
        return servicedao;
    }

    public void setServicedao(Servicedao servicedao) {
        this.servicedao = servicedao;
    }

    public Userdao getUserdao() {
        return userdao;
    }

    public void setUserdao(Userdao userdao) {
        this.userdao = userdao;
    }

    @Override
    public List<Mouvement> findAll() {
        try {
            return mouvementdao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceMouvementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void Delete(Long id) {
        try {
            Mouvement m = mouvementdao.findById(id);
            mouvementdao.delete(m);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceMouvementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
