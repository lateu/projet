package com.lateu.projet.afriland;

import com.douwe.generic.dao.impl.GenericDao;
import com.douwe.projet.tutore.exercice.dao.DataAccessException;
import com.lateu.projet.afriland.dao.CreditScolairedao;
import com.lateu.projet.afriland.daoImpl.CreditScolairedaoImpl;
import com.lateu.projet.afriland.entities.CreditScolaire;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DataAccessException, com.douwe.generic.dao.DataAccessException
    {
           
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("AfriPU");
       EntityManager em=emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
      tx.begin();
      
       CreditScolairedao crdao=new CreditScolairedaoImpl();
      ((GenericDao) crdao).setManager(em);
      
      //CreditScolaire c=new CreditScolaire("0458", "lateu richard", null, null, "en cours", "malaika", null, 0.5);
      
      //crdao.create(c);
        tx.commit();
       em.close();
        emf.close();
    }
}
