package main.business;

import java.util.concurrent.atomic.AtomicLong;

public class TypeChambre {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private String libelle;

    public TypeChambre(String libelle) {
        this.id = idGenerator.incrementAndGet();
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "TypeChambre{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
