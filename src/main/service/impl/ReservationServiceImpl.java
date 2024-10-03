package main.service.impl;

import main.business.Reservation;
import main.repository.GenericRepository;
import main.service.ReservationService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    private static final GenericRepository<Reservation> reservationRepository = GenericRepository.getInstance(Reservation.class);

    @Override
    public void createReservation(Reservation reservation) {
        List<Reservation> reservations = reservationRepository.getAll();

        // Check if the room is available
        for (Reservation r : reservations) {
            if (r.getChambre().getId().equals(reservation.getChambre().getId()) &&
                    (reservation.getDateDebut().before(r.getDateFin()) && reservation.getDateFin().after(r.getDateDebut()))) {
                throw new IllegalArgumentException("La chambre n'est pas disponible pour cette période");
            }
        }

        reservationRepository.save(reservation);
    }
}
