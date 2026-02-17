package com.example.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "categories")
public class Categorie {

    @Id //Pour générer l'instanciation unique
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nom;
    @OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
    private List<Produit> produits = new ArrayList<>();

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
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
        this.nom = nom;  // Modifie le nom de la catégorie
    }

    public List<Produit> getProduits() {
        return produits;  // Retourne tous les produits de cette catégorie
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;  // Remplace la liste des produits par une nouvelle liste
    }

    public void addProduit(Produit produit) {
        produits.add(produit);
        produit.setCategorie(this);
    }

    //Supprimer lorsqu'il serait détécté après'
    public void removeProduit(Produit produit) {
        produits.remove(produit);
        produit.setCategorie(null);
    }

}