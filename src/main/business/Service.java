package main.business;

import main.utils.Entity;

public class Service extends Entity {
    private String libelle;

    public Service(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + getId() +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
