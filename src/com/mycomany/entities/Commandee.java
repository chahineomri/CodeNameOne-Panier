/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Amirov
 */
public class Commandee {
    
      private int id;
    private String nomutilisateur; 
    private String etat;
    private String datecreation;
    private int totale;

    public Commandee() {
    }

    public Commandee(String nomutilisateur, String etat, String datecreation, int totale) {
        this.nomutilisateur = nomutilisateur;
        this.etat = etat;
        this.datecreation = datecreation;
        this.totale = totale;
    }
    

    public Commandee(int id, String nomutilisateur, String etat, String datecreation, int totale) {
        this.id = id;
        this.nomutilisateur = nomutilisateur;
        this.etat = etat;
        this.datecreation = datecreation;
        this.totale = totale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", nomutilisateur=" + nomutilisateur + ", etat=" + etat + ", datecreation=" + datecreation + ", totale=" + totale + '}';
    }
    
    
    
    
    
}
