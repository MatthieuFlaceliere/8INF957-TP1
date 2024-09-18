package main.business;

import java.util.concurrent.atomic.AtomicLong;

public class Chambre {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private int prix;
    private TypeChambre typeChambre;
    private TypeHebergement typeHebergement;

    public Chambre(int prix, TypeChambre typeChambre, TypeHebergement typeHebergement) {
        this.id = idGenerator.incrementAndGet();
        this.prix = prix;
        this.typeChambre = typeChambre;
        this.typeHebergement = typeHebergement;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", prix=" + prix +
                ", typeChambre=" + typeChambre +
                ", typeHebergement=" + typeHebergement +
                '}';
    }
}
