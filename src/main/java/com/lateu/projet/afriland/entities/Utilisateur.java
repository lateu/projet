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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/*
 *
 * @author lateu
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findbyUsername", query = "select p from Utilisateur p where (p.username=:username )"),
    @NamedQuery(name = "findMe", query = "select p from Utilisateur p where (p.username=:username )AND(p.password=:password )"),
    @NamedQuery(name = "findbyByUserId", query = "select u from UserAutority u where (u.utilisateur.id=:id)"),
   // @NamedQuery(name = "findRoleByIdUser", query = "select u from UserAutority u ,Utilisateur usr join u.utilisateur u_usr where usr.id=:id AND u_usr=:id"),
    @NamedQuery(name = "findUserByService", query = "select u from Utilisateur u, Service s   where (s.nom=:nom)AND(u.service.id=s.id)"),
    @NamedQuery(name = "redirection", query = "select r from UserAutority r,Utilisateur u join r.utilisateur r_u  where (u.username=:username )AND(r_u.id=u.id)"),
    @NamedQuery(name = "findbyUseByAgence", query = "select p from Utilisateur p,Agence a,Service s where(p.service.id=s.id )AND(s.agence.Code=:codeAgence)"),})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.ALL})
    private List<Control> controls;
    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.ALL})
    private List<UserAutority> userAutoritys;
    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.ALL})
    private List<CreditScolaire> creditScolaires;
    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.ALL})
    private List<Poubelle> poubelles;
    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.ALL})
    private List<Mouvement> mouvements;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Service service;
    private String password;
    private int AccountState;
    @Column(length = 300)
    private String nom;

    public Utilisateur() {
    }

    public Utilisateur(String username, Service service, String password, int AccountState, String nom) {
        this.username = username;
        this.service = service;
        this.password = password;
        this.AccountState = AccountState;
        this.nom = nom;
    }

    public List<Control> getControls() {
        return controls;
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAccountState() {
        return AccountState;
    }

    public void setAccountState(int AccountState) {
        this.AccountState = AccountState;
    }

    public List<UserAutority> getUserAutoritys() {
        return userAutoritys;
    }

    public void setUserAutoritys(List<UserAutority> userAutoritys) {
        this.userAutoritys = userAutoritys;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Mouvement> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<Mouvement> mouvements) {
        this.mouvements = mouvements;
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public List<CreditScolaire> getCreditScolaires() {
        return creditScolaires;
    }

    public void setCreditScolaires(List<CreditScolaire> creditScolaires) {
        this.creditScolaires = creditScolaires;
    }

    public List<Poubelle> getPoubelles() {
        return poubelles;
    }

    public void setPoubelles(List<Poubelle> poubelles) {
        this.poubelles = poubelles;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", service=" + service + ", password=" + password + ", AccountState=" + AccountState + ", nom=" + nom + '}';
    }

    
    
    
}
