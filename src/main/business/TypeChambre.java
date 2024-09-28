package main.business;

import main.utils.Entity;

public class TypeChambre extends Entity {
    private String libelle;

    public TypeChambre(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "TypeChambre{" +
                "id=" + getId() +
                ", libelle='" + libelle + '\'' +
                '}';
    }

    public String getLibelle(){
        return this.libelle;
    }
}
