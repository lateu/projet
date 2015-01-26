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
  @NamedQuery(name = "findAnomalie", query = "select p from Anomalie p where p.PersonneCor=:correcteur"),

})
public class Anomalie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String PersonneInit;
    private String PersonneCor;
    @Column(length = 500, nullable = false)
    private String Motif;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOp;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCo;
    @Column(length = 500)
    private String solution;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private DossierCreationCompte dossier;

    public Anomalie() {
    }

    public String getPersonneInit() {
        return PersonneInit;
    }

    public void setPersonneInit(String PersonneInit) {
        this.PersonneInit = PersonneInit;
    }

    public String getPersonneCor() {
        return PersonneCor;
    }

    public void setPersonneCor(String PersonneCor) {
        this.PersonneCor = PersonneCor;
    }

    public Date getDateOp() {
        return dateOp;
    }

    public void setDateOp(Date dateOp) {
        this.dateOp = dateOp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return Motif;
    }

    public void setMotif(String Motif) {
        this.Motif = Motif;
    }

    public DossierCreationCompte getDossier() {
        return dossier;
    }

    public void setDossier(DossierCreationCompte dossier) {
        this.dossier = dossier;
    }

    public Date getDateCo() {
        return dateCo;
    }

    public void setDateCo(Date dateCo) {
        this.dateCo = dateCo;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Anomalie{" + "id=" + id + ", PersonneInit=" + PersonneInit + ", PersonneCor=" + PersonneCor + ", Motif=" + Motif + ", dateOp=" + dateOp + ", dateCo=" + dateCo + ", solution=" + solution + '}';
    }

   
}
