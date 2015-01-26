/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.lateu.projet.afriland.entities.Agence;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceAgence {

    public void create(Agence a);
public Agence findByCode(String codeAgence);
public Agence findAgenceByUsername(String username);
    public List<Agence> findAll();
    public Agence findById(Long id);
}
