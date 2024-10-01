package main.business;


import main.utils.Entity;

public class TypeHebergement extends Entity {
    private String libelle;

    public TypeHebergement(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
