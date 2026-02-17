package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
@Entity
@Table(name = "Produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private BigDecimal prix;


    @ManyToOne// C'est l'inverse de @OneToMany dans Categorie
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Produit() {
    }
    public Produit(String nom, BigDecimal prix, Categorie categorie) {
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" +  // Début de la chaîne
                "id=" + id +  // Ajoute l'ID
                ", nom='" + nom + '\'' +  // Ajoute le nom
                ", prix=" + prix +  // Ajoute le prix
                ", categorie=" + (categorie != null ? categorie.getNom() : "null") +
                '}';
    }
}