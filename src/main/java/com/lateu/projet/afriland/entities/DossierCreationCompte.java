/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findForCA", query = "select p from DossierCreationCompte p where (p.pas5='ok')AND(p.pas1='En cours') ORDER BY id DESC"),
    @NamedQuery(name = "findForJR", query = "select p from DossierCreationCompte p where (p.pas1='ok')AND(p.pas2='En cours')"),
    @NamedQuery(name = "findForINF", query = "select p from DossierCreationCompte p where (p.pas2='ok')AND(p.pas3='En cours')"),
    @NamedQuery(name = "findForAR", query = "select p from DossierCreationCompte p where (p.pas3='ok')AND(p.pas4='En cours')"),
    @NamedQuery(name = "findAnomalieByAgence", query = "select p from Anomalie p,DossierCreationCompte d where (p.dossier.id=d.id)AND(d.agence.Code=:codeAgence)"),
    @NamedQuery(name = "findByPeriode", query = "select p from DossierCreationCompte p where p.dateCreation BETWEEN :debut and :fin "),
    @NamedQuery(name = "findAction", query = "select p from Control p where (p.dossier.compte=:compte)"),
    @NamedQuery(name = "findForALL", query = "select p from DossierCreationCompte p  ORDER BY id DESC"),
    @NamedQuery(name = "findAccueil", query = "select p from DossierCreationCompte p where (p.pas5='En cours')"),
 //   @NamedQuery(name = "annuler", query = "select p from DossierCreationCompte p where (p.pas5='En cours')"),
})
public class DossierCreationCompte extends Dossier implements Serializable {

    @OneToMany(mappedBy = "dossier", cascade = {CascadeType.ALL})
    private List<Anomalie> anomalies;
    @OneToMany(mappedBy = "dossier", cascade = {CascadeType.ALL})
    private List<Control> controls;
    private String pas1;
    private String pas2;
    private String pas3;
    private String pas4;
    private String pas5;
    private String cle;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMiseEnCirculation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Agence agence;

    public DossierCreationCompte(String pas1, String pas2, String pas3, String pas4, String pas5, String cle, Date dateCreation, Date dateMiseEnCirculation, Agence agence, String compte, String Client) {
        super(compte, Client);
        this.pas1 = pas1;
        this.pas2 = pas2;
        this.pas3 = pas3;
        this.pas4 = pas4;
        this.pas5 = pas5;
        this.cle = cle;
        this.dateCreation = dateCreation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.agence = agence;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public DossierCreationCompte() {
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setDateMiseEnCirculation(Date dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public List<Anomalie> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomalie> anomalies) {
        this.anomalies = anomalies;
    }

    public List<Control> getControls() {
        return controls;
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }

    public String getPas1() {
        return pas1;
    }

    public void setPas1(String pas1) {
        this.pas1 = pas1;
    }

    public String getPas2() {
        return pas2;
    }

    public void setPas2(String pas2) {
        this.pas2 = pas2;
    }

    public String getPas3() {
        return pas3;
    }

    public void setPas3(String pas3) {
        this.pas3 = pas3;
    }

    public String getPas4() {
        return pas4;
    }

    public void setPas4(String pas4) {
        this.pas4 = pas4;
    }

    public String getPas5() {
        return pas5;
    }

    public void setPas5(String pas5) {
        this.pas5 = pas5;
    }

    @Override
    public String toString() {
        return "DossierCreationCompte{" + "pas1=" + pas1 + ", pas2=" + pas2 + ", pas3=" + pas3 + ", pas4=" + pas4 + ", cle=" + cle + ", dateCreation=" + dateCreation + ", dateMiseEnCirculation=" + dateMiseEnCirculation + ", agence=" + agence + '}';
    }
}
