package main.business;

import java.util.concurrent.atomic.AtomicLong;

public class Client {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;

    public Client(String nom, String prenom, String email, String telephone, String adresse) {
        this.id = idGenerator.incrementAndGet();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
