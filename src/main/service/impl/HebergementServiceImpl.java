package main.service.impl;

import main.business.Hebergement;
import main.business.Reservation;
import main.business.TypeHebergement;
import main.repository.GenericRepository;
import main.service.HebergementService;
import main.utils.Filter;

import java.util.*;

public class HebergementServiceImpl implements HebergementService {
    private static final GenericRepository<Hebergement> hebergementRepository = GenericRepository.getInstance(Hebergement.class);
    private static final GenericRepository<TypeHebergement> typeHebergementRepository = GenericRepository.getInstance(TypeHebergement.class);
    private static final GenericRepository<Reservation> reservationRepository = GenericRepository.getInstance(Reservation.class);

    @Override
    public List<Hebergement> getByFilter(Filter filter) {
        List<Hebergement> hebergements = new ArrayList<>(hebergementRepository.getAll());
        List<Reservation> reservations = reservationRepository.getAll();

        if (filter == null) {
            return hebergements;
        }

        // Filter by type
        if (filter.getType() != null) {
            hebergements.removeIf(hebergement -> !Objects.equals(hebergement.getType().getId(), filter.getType().getId()));
        }

        // Filter by address
        if (filter.getAdresse() != null) {
            hebergements.removeIf(hebergement -> !Objects.equals(hebergement.getAdresse().getId(), filter.getAdresse().getId()));
        }

        // Filter by room type
        if (filter.getTypeChambre() != null) {
            hebergements.removeIf(hebergement -> hebergement.getChambres().stream().noneMatch(chambre -> Objects.equals(chambre.getType().getId(), filter.getTypeChambre().getId())));
        }

        // Filter by services
        if (filter.getServices() != null) {
            hebergements.removeIf(hebergement -> !new HashSet<>(hebergement.getServices()).containsAll(filter.getServices()));
        }

        // Remove chambre if already reserved
        if (filter.getDateDebut() != null && filter.getDateFin() != null) {
            for (Hebergement hebergement : hebergements) {
                hebergement.getChambres().removeIf(chambre ->
                        reservations.stream().anyMatch(reservation ->
                                reservation.getChambre().getId().equals(chambre.getId()) &&
                                datesRangeValid(filter.getDateDebut(), filter.getDateFin(), reservation.getDateDebut(), reservation.getDateFin())
                        )
                );
            }
        }

        return hebergements;
    }

    private static boolean datesRangeValid(Date dateDebut, Date dateFin, Date dateDebutTest, Date dateFinTest) {
        return dateFin.before(dateDebut) || dateFinTest.after(dateDebutTest);
    }

    @Override
    public List<Hebergement> getAll() {
        return hebergementRepository.getAll();
    }

    @Override
    public List<TypeHebergement> getAllType() {
        return typeHebergementRepository.getAll();
    }
}
