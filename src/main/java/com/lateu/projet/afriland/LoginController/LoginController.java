package com.lateu.projet.afriland.LoginController;

import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.metier.ServiceUtilisateur;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lateu
 */
@ManagedBean
@SessionScoped
@Controller

public class LoginController implements Serializable{

    public static String a;
    private String conncte;
    ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-conf.xml");
    private ServiceUtilisateur serUser = (ServiceUtilisateur) ctx.getBean("ServiceUtilisateur");

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // String name = user.getUsername();
        a = user.getUsername();
        model.addAttribute("lateu", a);
        model.addAttribute("message", "Spring Security login + database example");

        UserAutority client;
        client = serUser.redirection(a);
        if (client.getAutorite().equals("ROLE_ADMIN")) {
            return "vues/Admin/index";
        } else if (client.getAutorite().equals("ROLE_JR")) {
            return "vues/DossierCreation/index";
        } else if (client.getAutorite().equals("ROLE_CA")) {
            return "vues/DossierCreation/index";
        } else if (client.getAutorite().equals("ROLE_INF")) {
            return "vues/DossierCreation/index";
        } else if (client.getAutorite().equals("ROLE_AR")) {
           
            return "vues/Archiviste/index";
        } else if (client.getAutorite().equals("ROLE_CDS")) {
            return "vues/CreditScolaire/Dossier/index";
        } else if(client.getAutorite().equals("ROLE_CONS")){
            return "vues/Accueil/Accueil";
        }else if(client.getAutorite().equals("ROLE_CDP")){
         return "vues/DossierCredit/AccueilDossierCredit";
        }else{
            return "vues/login";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "vues/login";

    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "vues/login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        return "vues/login";

    }

    public String getConncte() {
     conncte = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return conncte;
       
    }
    

    public void setConncte(String conncte) {
        this.conncte = conncte;
    }

    public ServiceUtilisateur getSerUser() {
        return serUser;
    }

    public void setSerUser(ServiceUtilisateur serUser) {
        this.serUser = serUser;
    }
}
