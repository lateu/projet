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
    @NamedQuery(name = "Mouvement.findAll", query = "SELECT m FROM Mouvement m"),
    @NamedQuery(name = "Mouvement.findById", query = "SELECT m FROM Mouvement m WHERE m.id = :id"),
    @NamedQuery(name = "Mouvement.findByDateMovement", query = "SELECT m FROM Mouvement m WHERE m.dateMovement = :dateMovement")})
public class Mouvement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMovement;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Archivage archive;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Service service;
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Utilisateur utilisateur;
    public Mouvement() {
    }

    public Mouvement(Date dateMovement, Archivage archive, Service service, Utilisateur utilisateur) {
        this.dateMovement = dateMovement;
        this.archive = archive;
        this.service = service;
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    public Date getDateMovement() {
        return dateMovement;
    }

    public void setDateMovement(Date dateMovement) {
        this.dateMovement = dateMovement;
    }

    public Archivage getArchive() {
        return archive;
    }

    public void setArchive(Archivage archive) {
        this.archive = archive;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


  
}
