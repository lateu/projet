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

/**
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    //@NamedQuery(name = "Archivage.findAll", query = "SELECT a FROM Archivage a"),
    @NamedQuery(name = "Archivage.findById", query = "SELECT a FROM Archivage a WHERE a.id = :id"),
    @NamedQuery(name = "Archivage.findByAccount", query = "SELECT a FROM Archivage a WHERE a.compte = :Account"), // @NamedQuery(name = "Archivage.findByIntitule", query = "SELECT a FROM Archivage a WHERE a.Intitule=:intitule"),
//  @NamedQuery(name = "findCredit_S_Archiable", query = "SELECT a FROM CreditScolaire a WHERE a.Valider='ok' AND(a.archiver=0)")
})
public class Archivage extends Dossier implements Serializable {

    private String position;
    @OneToMany(mappedBy = "archive", cascade = {CascadeType.ALL})
    private List<Mouvement> mouvements;
    private String typeDocument;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Agence agence;

    public Archivage(String position, List<Mouvement> mouvements, String typeDocument, Agence agence, String compte, String Client) {
        super(compte, Client);
        this.position = position;
        this.mouvements = mouvements;
        this.typeDocument = typeDocument;
        this.agence = agence;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

  
    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    
    public Archivage() {
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

   

    public List<Mouvement> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<Mouvement> mouvements) {
        this.mouvements = mouvements;
    }

    @Override
    public String toString() {
        return "Archivage{" + "id=" + super.getId() + " position=" + position + ", typeDocument=" + typeDocument + '}';
    }
}
