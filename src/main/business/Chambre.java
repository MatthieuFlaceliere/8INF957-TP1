package main.business;

import main.utils.Entity;

public class Chambre extends Entity {
    private int prix;
    private TypeChambre typeChambre;
    private Hebergement hebergement;

    public Chambre(int prix, TypeChambre typeChambre, Hebergement hebergement) {
        this.prix = prix;
        this.typeChambre = typeChambre;
        this.hebergement = hebergement;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + getId() +
                ", prix=" + prix +
                ", typeChambre=" + typeChambre +
                ", typeHebergement=" + hebergement +
                '}';
    }
}
