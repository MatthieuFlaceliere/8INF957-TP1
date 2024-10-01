package main.business;

import main.utils.Entity;

public class Client extends Entity {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;

    public Client(String nom, String prenom, String email, String telephone, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + email + ", " + telephone + ", " + adresse + ")";
    }
}
