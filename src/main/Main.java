package main;

import main.business.*;
import main.init.InitData;
import main.repository.GenericRepository;
import main.service.HebergementService;
import main.service.ReservationService;
import main.service.impl.HebergementServiceImpl;
import main.service.impl.ReservationServiceImpl;
import main.utils.Filter;

import java.util.Date;
import java.util.List;

public class Main {
    private static final HebergementService hebergementService = new HebergementServiceImpl();
    private static final ReservationService reservationService = new ReservationServiceImpl();
    private static final int NB_HEBERGEMENTS = 100;
    private static final Date DATE_DEBUT = new Date();
    private static final Date DATE_FIN = new Date(DATE_DEBUT.getTime() + 1000 * 60 * 60 * 24 * 7); // 7 jours après

    public static void main(String[] args) {
        printLine();
        System.out.println("\nCréation de " + NB_HEBERGEMENTS + " hébergements");
        new InitData(NB_HEBERGEMENTS);
        System.out.println("Fin de la création " + hebergementService.getAll().size() + " hébergements \n");

        printLine();

        System.out.println("\nTest filtre sur les hébergements \n");
        Filter filter = new Filter.Builder()
                .setType(hebergementService.getAllType().get(0))
                .setServices(GenericRepository.getInstance(Service.class).getAll().subList(0, 2))
                .setDateDebut(DATE_DEBUT)
                .setDateFin(DATE_FIN)
                .setMaxPrix(1000)
                .build();
        System.out.println("Filtre: " + filter + "\n");

        List<Hebergement> hebergements = hebergementService.getByFilter(filter);
        System.out.println("Résultat du filtre: " + hebergements.size() + " hébergements \n");
        for (Hebergement hebergement : hebergements) {
            System.out.println(hebergement);
        }

        printLine();

        System.out.println("\nTest reservation d'une chambre \n");

        Client client = new Client("Jean", "Dupont", "jeandupont@mail.com", "0123456789", "17 rue de la paix");
        if (hebergements.isEmpty()) {
            hebergements = hebergementService.getAll();
        }
        Reservation reservation = new Reservation(DATE_DEBUT, DATE_FIN, client, hebergements.get(0).getChambres().get(0), hebergements.get(0));

        System.out.println("Réservation: " + reservation + "\n");

        try {
            reservationService.createReservation(reservation);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        System.out.println("Réservation effectuée avec succès \n");

        printLine();

        System.out.println("\nTest reservation d'une chambre déjà réservée \n");

        System.out.println("Réservation: " + reservation + "\n");

        try {
            reservationService.createReservation(reservation);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        printLine();

        System.out.println("\nHerbergements après réservation la chambre n'est plus disponible \n");
        List<Hebergement> hebergementsAfterReservation = hebergementService.getByFilter(filter);
        for (Hebergement hebergement : hebergementsAfterReservation) {
            System.out.println(hebergement);
        }

        printLine();

        System.out.println("\nAnnulation de la réservation \n");
        reservationService.cancelReservation(reservation);
        System.out.println("Réservation annulée avec succès \n");

        printLine();

        System.out.println("\nHerbergements après annulation de la réservation la chambre est de nouveau disponible \n");
        List<Hebergement> hebergementsAfterCancelReservation = hebergementService.getByFilter(filter);
        for (Hebergement hebergement : hebergementsAfterCancelReservation) {
            System.out.println(hebergement);
        }

    }

    private static void printLine() {
        System.out.println("-".repeat(100));
    }
}