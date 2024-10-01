package main.business;

import main.utils.Entity;

public class TypeChambre extends Entity {
    private String libelle;

    public TypeChambre(String libelle) {
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
