/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.Autoritedao;
import com.lateu.projet.afriland.dao.Poubelledao;
import com.lateu.projet.afriland.dao.Servicedao;
import com.lateu.projet.afriland.dao.Userdao;
import com.lateu.projet.afriland.entities.Poubelle;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceUtilisateur;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceUtilisateurImpl implements ServiceUtilisateur {

    private Userdao userdao;
    private Autoritedao autoritedao;
    private Poubelledao poubelledao;
    private Servicedao servicedao;

    @Override
    public Utilisateur findByUsername(String usename) {
        return userdao.findbyUsername(usename);
    }

    public Userdao getUserdao() {
        return userdao;
    }

    public void setUserdao(Userdao userdao) {
        this.userdao = userdao;
    }

    public Autoritedao getAutoritedao() {
        return autoritedao;
    }

    public void setAutoritedao(Autoritedao autoritedao) {
        this.autoritedao = autoritedao;
    }

    public Poubelledao getPoubelledao() {
        return poubelledao;
    }

    public void setPoubelledao(Poubelledao poubelledao) {
        this.poubelledao = poubelledao;
    }

    @Override
    public void create(Utilisateur utilisateur, String autority, String nomService) {
        try {
            UserAutority userAutorite = new UserAutority();
            userAutorite.setAutorite(autority);
            userAutorite.setUtilisateur(utilisateur);
            utilisateur.setService(servicedao.findbyNom(nomService));
            userdao.create(utilisateur);
            autoritedao.create(userAutorite);

        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Utilisateur> findAll() {
        try {
            return userdao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UserAutority redirection(String usename) {
        return userdao.findbyStatut(usename);
    }

    @Override
    public void update(Utilisateur u, String autority, String nomService) {
        try {
            //  UserAutority u = serviceUtilisateur.findByUserId(utilisateurSelect.getId());
            Service s = new Service();
            Utilisateur old = userdao.findById(u.getId());
            //      if(autority.equals("choisir")==false){
            //      old.s
            //      }
            //          
            if (nomService.equals("choisir") == false) {
                s = servicedao.findbyNom(nomService);
            } else {
                s = servicedao.findById(old.getService().getId());


            }
            u.setService(s);
            userdao.update(u);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public UserAutority findByUserId(Long id) {
        return autoritedao.findByUserId(id);
    }

    @Override
    public void delete(Long id) {
        try {
            autoritedao.delete(autoritedao.findByUserId(id));
            userdao.delete(userdao.findById(id));
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void creerPoubelle(Poubelle pb, Utilisateur u) {
        try {
            pb.setUtilisateur(u);
            poubelledao.create(pb);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Servicedao getServicedao() {
        return servicedao;
    }

    public void setServicedao(Servicedao servicedao) {
        this.servicedao = servicedao;
    }

    @Override
    public List<Poubelle> findAllPoubelle() {
        try {
            return poubelledao.findAll();
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Utilisateur findById(Long id) {
        try {
            return userdao.findById(id);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Utilisateur> findMe(String username, String password) {
        return userdao.findMe(username, password);

    }

    @Override
    public void ChangePassword(Utilisateur u) {
        try {
            userdao.update(u);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Utilisateur> findUserByService(String nom) {

        return userdao.findUserByService(nom);
    }

    @Override
    public List<Utilisateur> TestUsername(String username) {
        return userdao.TestUsername(username);
    }

    @Override
    public void deleteCreditScolaire(Long id, Poubelle p, String username) {
    //s
    
    }

    @Override
    public void InitPassord(String username) {
        try {
            Utilisateur old=userdao.findbyUsername(username);
            old.setPassword("xxxx");
            userdao.update(old);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public List<Utilisateur> findUserByAgence(String codeAgence) {
   return userdao.findUserByAgence(codeAgence);
    }

    @Override
    public void deleteMonRole(String username) {
        try {
            userdao.delete(userdao.findbyUsername(username));
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceUtilisateurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
     @Override
    public void createPoubelle(Poubelle p,Utilisateur u) {
        p.setUtilisateur(u);
        try {
            poubelledao.create(p);
        } catch (DataAccessException ex) {
            Logger.getLogger(ServicePoubelleImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
