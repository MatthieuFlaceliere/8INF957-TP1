package main.business;

import java.util.concurrent.atomic.AtomicLong;

public class Service {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private String libelle;

    public Service(String libelle) {
        this.id = idGenerator.incrementAndGet();
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
