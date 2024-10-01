package main.service;

import main.business.*;
import main.utils.Filter;

import java.util.List;

public interface HebergementService {
    List<Hebergement> getByFilter(Filter filter);
    List<Hebergement> getAll();
    List<TypeHebergement> getAllType();
}
