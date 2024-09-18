package main.business;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Reservation {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private int prixTotal;
    private Client client;
    private Chambre chambre;

    public Reservation(Date dateDebut, Date dateFin, int prixTotal, Client client, Chambre chambre) {
        this.id = idGenerator.incrementAndGet();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.client = client;
        this.chambre = chambre;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prixTotal=" + prixTotal +
                ", client=" + client +
                ", chambre=" + chambre +
                '}';
    }
}
