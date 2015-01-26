/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.daoImpl;

import com.douwe.generic.dao.DataAccessException;
import com.douwe.generic.dao.impl.GenericDao;
import com.lateu.projet.afriland.dao.Anomaliedao;
import com.lateu.projet.afriland.entities.Anomalie;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lateu
 */
public class AnomaliedaoImpl extends GenericDao<Anomalie, Long> implements Anomaliedao{

    @Override
    public List<Anomalie> findAnomalieByAgence(String codeAgence) {
        try {
            return   getManager().createNamedQuery("findAnomalieByAgence")
                                   .setParameter("codeAgence", codeAgence)
                                   .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(AnomaliedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Anomalie> findAnomalie(String correcteur) {
        try {
            return   getManager().createNamedQuery("findAnomalie")
                                            .setParameter("correcteur", correcteur)
                                            .getResultList();
        } catch (DataAccessException ex) {
            Logger.getLogger(AnomaliedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
