package main.service;

import main.business.Reservation;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
}
