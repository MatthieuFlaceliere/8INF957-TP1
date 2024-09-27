package main.business;


import main.utils.Entity;

public class TypeHebergement extends Entity {
    private String libelle;

    public TypeHebergement(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "TypeHebergement{" +
                "id=" + getId() +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
