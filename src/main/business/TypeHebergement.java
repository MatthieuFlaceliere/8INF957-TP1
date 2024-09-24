package main.business;

import java.util.concurrent.atomic.AtomicLong;

public class TypeHebergement {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private String libelle;

    public TypeHebergement(String libelle) {
        this.id = idGenerator.incrementAndGet();
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TypeHebergement{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
