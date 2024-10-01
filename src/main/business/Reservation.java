package main.business;

import main.utils.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation extends Entity {
    private Date dateDebut;
    private Date dateFin;
    private int prixTotal;
    private Client client;
    private Chambre chambre;

    public Reservation(Date dateDebut, Date dateFin, Client client, Chambre chambre) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        int nbJours = (int) ((dateFin.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24));
        this.prixTotal = nbJours * chambre.getPrix();
        this.client = client;
        this.chambre = chambre;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Reservation " + getId() + " du " + sdf.format(dateDebut) + " au " + sdf.format(dateFin) + " pour " + client + " dans la chambre " + chambre + " pour un total de " + prixTotal + "$";
    }
}
