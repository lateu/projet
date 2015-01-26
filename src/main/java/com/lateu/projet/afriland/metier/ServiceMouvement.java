/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.lateu.projet.afriland.entities.Mouvement;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceMouvement {

    public void create(String archive, String service,Long idUser);

    public void Delete(Long id);

    public List<Mouvement> findAll();
}