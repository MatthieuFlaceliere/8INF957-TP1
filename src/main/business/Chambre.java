package main.business;

import main.utils.Entity;

public class Chambre extends Entity {
    private int prix;
    private TypeChambre type;

    public Chambre(int prix, TypeChambre typeChambre) {
        this.prix = prix;
        this.type = typeChambre;
    }

    public TypeChambre getType() {
        return type;
    }

    public int getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return type + " à " + prix + "$";
    }
}
