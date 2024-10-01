package main.service.impl;

import main.business.Hebergement;
import main.service.HebergementService;
import main.utils.Filter;

import java.util.List;

public class HebergementServiceImpl implements HebergementService {
    @Override
    public List<Hebergement> getHebergementsByFilter(Filter filter) {
        return List.of();
    }
}
