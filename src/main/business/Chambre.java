package main.business;

import main.utils.Entity;

public class Chambre extends Entity {
    private int prix;
    private TypeChambre typeChambre;

    public Chambre(int prix, TypeChambre typeChambre) {
        this.prix = prix;
        this.typeChambre = typeChambre;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + getId() +
                ", prix=" + prix +
                ", typeChambre=" + typeChambre +
                '}';
    }
}
