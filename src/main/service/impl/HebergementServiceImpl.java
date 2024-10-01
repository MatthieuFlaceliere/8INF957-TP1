package main.service.impl;

import main.business.Hebergement;
import main.business.TypeHebergement;
import main.repository.GenericRepository;
import main.service.HebergementService;
import main.utils.Filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class HebergementServiceImpl implements HebergementService {
    private static final GenericRepository<Hebergement> hebergementRepository = GenericRepository.getInstance(Hebergement.class);
    private static final GenericRepository<TypeHebergement> typeHebergementRepository = GenericRepository.getInstance(TypeHebergement.class);

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
