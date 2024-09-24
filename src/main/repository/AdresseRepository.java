package main.repository;

import main.business.Adresse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdresseRepository {
    private static final Map<Long, Adresse> adresses = new HashMap<>();

    public void save(Adresse adresse) {
        adresses.put(adresse.getId(), adresse);
    }

    public List<Adresse> getAll() {
        return List.copyOf(adresses.values());
    }
}
