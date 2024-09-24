package main.repository;

import main.business.TypeHebergement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeHebergementRepository {
    private static final Map<Long, TypeHebergement> typeHebergements = new HashMap<>();

    public void save(TypeHebergement typeHebergement) {
        typeHebergements.put(typeHebergement.getId(), typeHebergement);
    }

    public List<TypeHebergement> getAll() {
        return List.copyOf(typeHebergements.values());
    }
}
