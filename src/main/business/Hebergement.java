package main.business;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Hebergement {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private String nom;
    private TypeHebergement type;
    private Adresse adresse;
    private List<Service> services;
    private List<Chambre> chambres;

    public Hebergement(String nom,TypeHebergement type, Adresse adresse, List<Service> services, List<Chambre> chambres) {
        this.id = idGenerator.incrementAndGet();
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.services = services;
        this.chambres = chambres;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Hebergement{" +
                "id=" + id +
                ", nom=" + nom +
                ", type=" + type +
                ", adresse=" + adresse +
                ", services=" + services +
                ", chambres=" + chambres +
                '}';
    }
}
