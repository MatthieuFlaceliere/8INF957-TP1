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

    public static void main(String[] args) {
        System.out.println("Création de " + NB_HEBERGEMENTS + " hébergements");
        new InitData(NB_HEBERGEMENTS);
        System.out.println("Fin de la création " + hebergementService.getAll().size() + " hébergements \n\n");

        System.out.println("Test filtre sur les hébergements \n");
        Filter filter = new Filter.Builder()
                .setType(hebergementService.getAllType().get(0))
                .setServices(GenericRepository.getInstance(Service.class).getAll().subList(0, 2))
                .build();
        System.out.println("Filtre: " + filter + "\n");

        List<Hebergement> hebergements = hebergementService.getByFilter(filter);
        System.out.println("Résultat du filtre: " + hebergements.size() + " hébergements \n");
        for (Hebergement hebergement : hebergements) {
            System.out.println(hebergement);
        }

        System.out.println("\nTest reservation d'une chambre \n");

        Date dateDebut = new Date();
        Date dateFin = new Date(dateDebut.getTime() + 1000 * 60 * 60 * 24 * 7); // 7 jours après
        Client client = new Client("Jean", "Dupont", "jeandupont@mail.com", "0123456789", "17 rue de la paix");
        if (hebergements.isEmpty()) {
            hebergements = hebergementService.getAll();
        }
        Reservation reservation = new Reservation(dateDebut, dateFin, client, hebergements.get(0).getChambres().get(0));

        System.out.println("Réservation: " + reservation + "\n");

        reservationService.createReservation(reservation);

        System.out.println("Fin de la réservation \n\n");
    }
}