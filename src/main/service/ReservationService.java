package main.service;

import main.business.Chambre;
import main.business.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    void createReservation(Reservation reservation);

    void cancelReservation(Reservation reservation);

    boolean validDatesRange(Chambre chambre, Date dateDebut, Date fateFin);
}
