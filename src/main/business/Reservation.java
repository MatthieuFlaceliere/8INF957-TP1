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
    private Hebergement hebergement;

    public Reservation(Date dateDebut, Date dateFin, Client client, Chambre chambre, Hebergement hebergement) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        int nbJours = (int) ((dateFin.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24));
        this.prixTotal = nbJours * chambre.getPrix();
        this.client = client;
        this.chambre = chambre;
        this.hebergement = hebergement;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Reservation " + getId() + " du " + sdf.format(dateDebut) + " au " + sdf.format(dateFin) + " pour " + client + " dans la chambre " + chambre + " pour un total de " + prixTotal + "$";
    }
}
