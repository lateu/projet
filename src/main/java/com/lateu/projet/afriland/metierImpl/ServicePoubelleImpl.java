/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.lateu.projet.afriland.dao.Poubelledao;
import com.lateu.projet.afriland.dao.Userdao;
import com.lateu.projet.afriland.metier.ServiceCorbeille;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServicePoubelleImpl implements ServiceCorbeille{
    
    private Userdao userdao;

   private Poubelledao poubelledao;

    public Poubelledao getPoubelledao() {
        return poubelledao;
    }

    public void setPoubelledao(Poubelledao poubelledao) {
        this.poubelledao = poubelledao;
    }
   
    public Userdao getUserdao() {
        return userdao;
    }

    public void setUserdao(Userdao userdao) {
        this.userdao = userdao;
    }

   
    
    
    
    
}
