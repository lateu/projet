/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.Service;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface Servicedao extends IDao<Service, Long>{
    Service findbyNom(String s,String c);
   List<Service> TestService(String s,String c);
    List<Service> findServiceByAgence(String agence);
}
