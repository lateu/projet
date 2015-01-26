/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Service;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceService {

    public void create(Service c, Agence a);

    public void update(Service c);

     public void DeleteService(Service c);
    
    public List<Service> findAll();

    public List<Service> TestService(String s);

    public List<Service> FindServiceByagence(String s);

    public Service findByNomService(String nom);

    public Service findById(Long id);
}
