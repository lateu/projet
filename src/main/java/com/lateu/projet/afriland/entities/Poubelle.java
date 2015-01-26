/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author lateu
 */
@Entity
public class Poubelle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String InfoSuppr;
    @Column(length = 500,nullable = false)
    private String Motif;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Utilisateur utilisateur;

    public Poubelle() {
    }

    public Poubelle(String InfoSuppr, String Motif, Utilisateur utilisateur) {
        this.InfoSuppr = InfoSuppr;
        this.Motif = Motif;
        this.utilisateur = utilisateur;
    }

    public String getInfoSuppr() {
        return InfoSuppr;
    }

    public void setInfoSuppr(String InfoSuppr) {
        this.InfoSuppr = InfoSuppr;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

  

    public String getMotif() {
        return Motif;
    }

    public void setMotif(String Motif) {
        this.Motif = Motif;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
