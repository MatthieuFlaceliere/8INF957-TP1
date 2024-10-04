package main.business;

import main.utils.Entity;

import java.util.List;

public class Hebergement extends Entity {
    private String nom;
    private TypeHebergement type;
    private Adresse adresse;
    private List<Service> services;
    private List<Chambre> chambres;

    public Hebergement(String nom,TypeHebergement type, Adresse adresse, List<Service> services, List<Chambre> chambres) {
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.services = services;
        this.chambres = chambres;
    }

    public String getNom() {
        return nom;
    }

    public TypeHebergement getType() {
        return type;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    @Override
    public String toString() {
        return getId() + " - " + type + " " + nom + "\n" +
                "  Adresse: " + adresse + "\n" +
                "  Services: " + services + "\n" +
                "  Chambres disponibles: " + chambres;
    }
}
