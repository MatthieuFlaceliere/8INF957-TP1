package main.repository;

import main.business.Hebergement;

import java.util.*;

public class HebergementRepository {
    private static final Map<Long, Hebergement> hebergements  = new HashMap<>();

    public void save(Hebergement hebergement) {
        hebergements.put(hebergement.getId(), hebergement);
    }

    public List<Hebergement> getAll() {
        return List.copyOf(hebergements.values());
    }
}
