/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.metier.ServiceCreditScolaire;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
public class statCreditbean implements Serializable {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-conf.xml");
    private ServiceCreditScolaire servcr = (ServiceCreditScolaire) ctx.getBean("ServiceCreditScolaire");
    private PieChartModel pieModel;

    public statCreditbean() throws DataAccessException {
        createPieModel();
    }

    private void createPieModel() throws DataAccessException {
        int nbv = 0, nbe = 0, nbr = 0;
        pieModel = new PieChartModel();
        nbv = servcr.nbreDocValider(servcr.findAll());
        nbe = servcr.nbreDocEncours(servcr.findAll());
     //   nbr = servcr.ReclamationNumber(servcr.findAll());
        pieModel.set("Dossiers Validés", nbv);
        pieModel.set("Dossiers en Cours", nbe);
      //  pieModel.set("Reclamation", nbr);

    }

    public ApplicationContext getCtx() {
        return ctx;
    }

    public void setCtx(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public ServiceCreditScolaire getServcr() {
        return servcr;
    }

    public void setServcr(ServiceCreditScolaire servcr) {
        this.servcr = servcr;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
}
