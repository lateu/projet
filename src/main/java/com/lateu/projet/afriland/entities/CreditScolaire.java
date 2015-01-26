/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
  @NamedQuery(name = "findByAgence", query = "select p from CreditScolaire p where (p.agence.Code=:codeAgence)"),
  @NamedQuery(name = "findCreditByAgance", query = "select p from CreditScolaire p where (p.agence.Code=:codeAgence)"),
})
public class CreditScolaire extends Dossier implements Serializable {
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateRecption;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateDecisionFinale;
    private String Valider;
    private int contratAssurance;
    @Column(nullable = false)
    private int montantDemande;
    private int montantAccordé;
    private double commission;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Utilisateur utilisateur;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Agence agence;
    
    private String valideur;

    public CreditScolaire(Date dateRecption, Date dateDecisionFinale, String Valider, int contratAssurance, int montantDemande, int montantAccordé, double commission, Utilisateur utilisateur, Agence agence, String valideur, String compte, String Client) {
        super(compte, Client);
        this.dateRecption = dateRecption;
        this.dateDecisionFinale = dateDecisionFinale;
        this.Valider = Valider;
        this.contratAssurance = contratAssurance;
        this.montantDemande = montantDemande;
        this.montantAccordé = montantAccordé;
        this.commission = commission;
        this.utilisateur = utilisateur;
        this.agence = agence;
        this.valideur = valideur;
    }

   
  
    public String getValideur() {
        return valideur;
    }

    public void setValideur(String valideur) {
        this.valideur = valideur;
    }



    

   
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(int montantDemande) {
        this.montantDemande = montantDemande;
    }

    public int getMontantAccordé() {
        return montantAccordé;
    }

    public void setMontantAccordé(int montantAccordé) {
        this.montantAccordé = montantAccordé;
    }

    public CreditScolaire() {
    }

    public Date getDateRecption() {
        return dateRecption;
    }

    public void setDateRecption(Date dateRecption) {
        this.dateRecption = dateRecption;
    }

    public Date getDateDecisionFinale() {
        return dateDecisionFinale;
    }

    public void setDateDecisionFinale(Date dateDecisionFinale) {
        this.dateDecisionFinale = dateDecisionFinale;
    }

    public String getValider() {
        return Valider;
    }

    public void setValider(String Valider) {
        this.Valider = Valider;
    }

    public int getContratAssurance() {
        return contratAssurance;
    }

    public void setContratAssurance(int contratAssurance) {
        this.contratAssurance = contratAssurance;
    }

   
    public double getCommission() {
        return commission;
    }

 
    public void setCommission(double commission) {
        this.commission = commission;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "CreditScolaire{"+ "dateRecption=" + dateRecption + ", dateDecisionFinale=" + dateDecisionFinale + ", Valider=" + Valider + ", contratAssurance=" + contratAssurance + ", montantDemande=" + montantDemande + ", montantAccord\u00e9=" + montantAccordé + ", commission=" + commission + ", utilisateur=" + utilisateur + ", agence=" + agence + ", valideur=" + valideur + '}';
    }

   
   

   
   
}
