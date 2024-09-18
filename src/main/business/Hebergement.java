package main.business;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Hebergement {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private TypeHebergement type;
    private Adresse adresse;
    private ArrayList<Service> services;
    private ArrayList<Chambre> chambres;

    public Hebergement(TypeHebergement type, Adresse adresse, ArrayList<Service> services, ArrayList<Chambre> chambres) {
        this.id = idGenerator.incrementAndGet();
        this.type = type;
        this.adresse = adresse;
        this.services = services;
        this.chambres = chambres;
    }

    @Override
    public String toString() {
        return "Hebergement{" +
                "id=" + id +
                ", type=" + type +
                ", adresse=" + adresse +
                ", services=" + services +
                ", chambres=" + chambres +
                '}';
    }
}
