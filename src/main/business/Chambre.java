package main.business;

import java.util.concurrent.atomic.AtomicLong;

public class Chambre {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private int prix;
    private TypeChambre typeChambre;
    private Hebergement hebergement;

    public Chambre(int prix, TypeChambre typeChambre, Hebergement hebergement) {
        this.id = idGenerator.incrementAndGet();
        this.prix = prix;
        this.typeChambre = typeChambre;
        this.hebergement = hebergement;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", prix=" + prix +
                ", typeChambre=" + typeChambre +
                ", typeHebergement=" + hebergement +
                '}';
    }
}
