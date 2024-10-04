package main.service.impl;

import main.business.Chambre;
import main.business.Hebergement;
import main.business.Reservation;
import main.business.TypeHebergement;
import main.repository.GenericRepository;
import main.service.HebergementService;
import main.service.ReservationService;
import main.utils.Filter;

import java.util.*;

public class HebergementServiceImpl implements HebergementService {
    private static final GenericRepository<Hebergement> hebergementRepository = GenericRepository.getInstance(Hebergement.class);
    private static final GenericRepository<TypeHebergement> typeHebergementRepository = GenericRepository.getInstance(TypeHebergement.class);
    private static final ReservationService reservationService = new ReservationServiceImpl();

    @Override
    public List<Hebergement> getByFilter(Filter filter) {
        List<Hebergement> hebergements = new ArrayList<>(hebergementRepository.getAll());

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

        // Remove chambre if already reserved and max price
        if (filter.getDateDebut() != null && filter.getDateFin() != null) {
            for (Hebergement hebergement : hebergements) {
                hebergement.getChambres().removeIf(chambre ->
                        reservationService.validDatesRange(chambre, filter.getDateDebut(), filter.getDateFin()) ||
                        chambre.getPrix() > filter.getMaxPrix()
                );
            }
        }

        return hebergements;
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
