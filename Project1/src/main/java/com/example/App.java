package com.example;

import com.example.model.Categorie;
import com.example.model.Produit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");

        // On Teste la relation ManyToOne
        testerRelationManyToOne(emf);

        // On Ferme la factory
        emf.close();
    }

    private static void testerRelationManyToOne(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Création d'une catégorie
            Categorie catInformatique = new Categorie("Informatique");

            em.persist(catInformatique);  // Enregistre la catégorie en base de données

            // Création de produits avec cette catégorie
            Produit laptop = new Produit("Laptop Dell", new BigDecimal("999.99"), catInformatique);

            Produit souris = new Produit("Souris sans fil", new BigDecimal("29.99"), catInformatique);

            // Enregistrement des produits
            em.persist(laptop);
            em.persist(souris);

            em.getTransaction().commit();

            System.out.println("Catégorie créée avec succès !");
            System.out.println("ID Catégorie: " + catInformatique.getId());

            System.out.println("\nProduits créés :");
            System.out.println(laptop);
            System.out.println(souris);

            // On récupère un produit et on affiche sa catégorie
            System.out.println("\n Test de la relation ");
            Produit produitRecupere = em.find(Produit.class, laptop.getId());

            System.out.println("Produit: " + produitRecupere.getNom());
            System.out.println("Catégorie: " + produitRecupere.getCategorie().getNom());

            // On récupère chaque catégorie avec ses produits
            System.out.println("\nProduits de la catégorie");
            Categorie categorieRecuperee = em.find(Categorie.class, catInformatique.getId());
            System.out.println("Catégorie: " + categorieRecuperee.getNom());
            System.out.println("Nombre de produits: " + categorieRecuperee.getProduits().size());

            // On parcourt tous les produits de la catégorie avec affichage
            for (Produit p : categorieRecuperee.getProduits()) {
                System.out.println("  - " + p.getNom() + ": " + p.getPrix() + "€");
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();  // Ferme l'EntityManager / la session
        }
    }

}