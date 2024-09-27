package main.business;

import main.utils.Entity;

import java.util.Date;

public class Reservation extends Entity {
    private Date dateDebut;
    private Date dateFin;
    private int prixTotal;
    private Client client;
    private Chambre chambre;

    public Reservation(Date dateDebut, Date dateFin, int prixTotal, Client client, Chambre chambre) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.client = client;
        this.chambre = chambre;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + getId() +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prixTotal=" + prixTotal +
                ", client=" + client +
                ", chambre=" + chambre +
                '}';
    }
}
