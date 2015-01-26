/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.projection;

/**
 *
 * @author lateu
 */
public class projectionCredit {

    private int nombre;
    private int demande;
    private int accord;
    private int temps;
    private int assurance;

    public int getAssurance() {
        return assurance;
    }

    public projectionCredit(int nombre, int demande, int accord, int temps, int assurance) {
        this.nombre = nombre;
        this.demande = demande;
        this.accord = accord;
        this.temps = temps;
        this.assurance = assurance;
    }
    
    
    public void setAssurance(int assurance) {
        this.assurance = assurance;
    }

   

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
  

    public projectionCredit() {
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getDemande() {
        return demande;
    }

    public void setDemande(int demande) {
        this.demande = demande;
    }

    public int getAccord() {
        return accord;
    }

    public void setAccord(int accord) {
        this.accord = accord;
    }

    @Override
    public String toString() {
        return "projectionCredit{" + "nombre=" + nombre + ", demande=" + demande + ", accord=" + accord + ", temps=" + temps + ", assurance=" + assurance + '}';
    }

   
   
    
    
    
}
