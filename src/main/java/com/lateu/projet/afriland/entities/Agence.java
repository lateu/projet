/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAgenceByCode", query = "SELECT a FROM Agence a WHERE a.Code = :codeAgence"),
    @NamedQuery(name = "findAgenceByUsername", query = "SELECT a FROM Agence a ,Utilisateur u,Service s WHERE (a.id=s.agence.id)AND(u.service.id=s.id)AND(u.username=:username)"),
    @NamedQuery(name = "findAgenceByUseId", query = "SELECT a FROM Agence a ,Utilisateur u,Service s WHERE (a.id=s.agence.id)AND(u.service.id=s.id)AND(u.id=:userId)"),
})
public class Agence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String Code;
    private String ville;
    @OneToMany(mappedBy = "agence", cascade = {CascadeType.ALL})
    private List<CreditScolaire> creditScolaires;
    @OneToMany(mappedBy = "agence", cascade = {CascadeType.ALL})
    private List<Service> services;
   
    @OneToMany(mappedBy = "agence", cascade = {CascadeType.ALL})
    private List<DossierCreationCompte> dossierCreationComptes; 
    @OneToMany(mappedBy = "agence", cascade = {CascadeType.ALL})
    private List<Archivage> archivages;
 @OneToMany(mappedBy = "agence", cascade = {CascadeType.ALL})
    private List<DossierCredit> dossierCredits;

    
    public Agence(String Code, String ville) {
        this.Code = Code;
        this.ville = ville;
    }

    public Agence() {
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

 

    public List<DossierCreationCompte> getDossierCreationComptes() {
        return dossierCreationComptes;
    }

    public void setDossierCreationComptes(List<DossierCreationCompte> dossierCreationComptes) {
        this.dossierCreationComptes = dossierCreationComptes;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<CreditScolaire> getCreditScolaires() {
        return creditScolaires;
    }

    public void setCreditScolaires(List<CreditScolaire> creditScolaires) {
        this.creditScolaires = creditScolaires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agence)) {
            return false;
        }
        Agence other = (Agence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public List<Archivage> getArchivages() {
        return archivages;
    }

    public void setArchivages(List<Archivage> archivages) {
        this.archivages = archivages;
    }

    public List<DossierCredit> getDossierCredits() {
        return dossierCredits;
    }

    public void setDossierCredits(List<DossierCredit> dossierCredits) {
        this.dossierCredits = dossierCredits;
    }

    
    
    
    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", Code=" + Code + ", ville=" + ville + '}';
    }
}
