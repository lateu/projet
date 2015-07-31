/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.test;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.metier.ServiceAgence;
import com.lateu.projet.afriland.metier.ServiceCreditScolaire;
import com.lateu.projet.afriland.metier.ServiceDossier;
import com.lateu.projet.afriland.metier.ServiceService;
import com.lateu.projet.afriland.metier.ServiceUtilisateur;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lateu
 */
public class TestService {
     private static ServiceCreditScolaire servCreditScolaire;
     private static ServiceService serviceService;
     private static   ServiceUtilisateur  serviceUtilisateur;
     private static ServiceAgence serviceAgence;
      private static   ServiceDossier sd;
      public static void main( String[] args ) throws DataAccessException, ParseException 
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-conf.xml");
        servCreditScolaire= (ServiceCreditScolaire) ctx.getBean("ServiceCreditScolaire");
       serviceService= (ServiceService) ctx.getBean("ServiceService");
       serviceUtilisateur= (ServiceUtilisateur) ctx.getBean("ServiceUtilisateur");
         serviceAgence= (ServiceAgence) ctx.getBean("ServiceAgence");
         sd=(ServiceDossier) ctx.getBean("ServiceDossier");
         Date d=new  Date();
       //  d = new SimpleDateFormat("HH:mm").parse("07:30");
       
       // Agence ag=new Agence("00008", "garoua");
         // serviceAgence.create(ag);
      //System.out.println(""+sd.findForCA().size());
     
        //CreditScolaire c=new CreditScolaire("0458", "lateu richard", d, d, "en cours", "malaika", null, 0.5);
      //System.out.println("="+sd.findForAccueil());
         // servCreditScolaire.create(c);
        // System.out.println("=="+servCreditScolaire.ReclamationNumber(servCreditScolaire.findAll()));
       //Agence a=serviceAgence.findByCode("00008");
      // Service s=new  Service("Service-Archive", "Emmanuel", a);
         //s.setAgence(a);
     // serviceService.create(s, a);
         
        // System.out.println("==="+serviceService.findAll());
   // s=serviceService.findByNomService("DR");
         
        // Utilisateur u=new Utilisateur(null, s, null, 1,null, null, null);
        //erviceUtilisateur.create(u, "ADMIN",s);
         
      //   System.out.println("="+serviceUtilisateur.findUserByService("JR"));
         
         
}
      
}
