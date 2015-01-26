/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.dao;

import com.douwe.generic.dao.IDao;
import com.lateu.projet.afriland.entities.Anomalie;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface Anomaliedao  extends IDao<Anomalie, Long>{
    List<Anomalie>findAnomalieByAgence(String codeAgence);
    List<Anomalie>findAnomalie(String correcteur);
}
