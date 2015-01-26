/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.lateu.projet.afriland.entities.Control;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceControl {
    
    public void Savecontrole(Control cn,String user,Long idDoc);
    List<Control> findControl();
}
