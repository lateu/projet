/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllCredit", query = "select p from DossierCredit p  ORDER BY id DESC"),
    @NamedQuery(name = "findForEnvoi", query = "select p from DossierCredit p where p.pas1='en cours'"),
    @NamedQuery(name = "findCreditForArchives", query = "select p from DossierCredit p where p.pas1='ok' AND p.pas2='En cours'"),
    @NamedQuery(name = "rappel", query = "select p from DossierCredit p where p.pas1='en cours'"),
})
public class DossierCredit extends Dossier implements Serializable {

    private String NoPret;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMiseEnPlace;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFirstEcheance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLastEcheance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Agence agence;
    private String pas1;
    private String pas2;

    public DossierCredit(String NoPret, Date dateMiseEnPlace, Date dateFirstEcheance, Date dateLastEcheance, Agence agence, String pas1, String pas2, String compte, String Client) {
        super(compte, Client);
        this.NoPret = NoPret;
        this.dateMiseEnPlace = dateMiseEnPlace;
        this.dateFirstEcheance = dateFirstEcheance;
        this.dateLastEcheance = dateLastEcheance;
        this.agence = agence;
        this.pas1 = pas1;
        this.pas2 = pas2;
    }

    public DossierCredit() {
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

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public String getNoPret() {
        return NoPret;
    }

    public void setNoPret(String NoPret) {
        this.NoPret = NoPret;
    }

    public Date getDateMiseEnPlace() {
        return dateMiseEnPlace;
    }

    public void setDateMiseEnPlace(Date dateMiseEnPlace) {
        this.dateMiseEnPlace = dateMiseEnPlace;
    }

    public Date getDateFirstEcheance() {
        return dateFirstEcheance;
    }

    public void setDateFirstEcheance(Date dateFirstEcheance) {
        this.dateFirstEcheance = dateFirstEcheance;
    }

    public Date getDateLastEcheance() {
        return dateLastEcheance;
    }

    public void setDateLastEcheance(Date dateLastEcheance) {
        this.dateLastEcheance = dateLastEcheance;
    }

    @Override
    public String toString() {
        return "DossierCredit{" + "NoPret=" + NoPret + ", dateMiseEnPlace=" + dateMiseEnPlace + ", dateFirstEcheance=" + dateFirstEcheance + ", dateLastEcheance=" + dateLastEcheance + ", agence=" + agence + ", pas1=" + pas1 + ", pas2=" + pas2 + '}';
    }
}
