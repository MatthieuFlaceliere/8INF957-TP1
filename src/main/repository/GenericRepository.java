package main.repository;

import main.utils.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepository <T extends Entity> {

    // Map pour stocker les instances singleton par type
    private static final Map<Class<?>, GenericRepository<?>> instances = new HashMap<>();
    
    // Stockage interne pour les entités
    private final Map<Long, T> storage = new HashMap<>();

    // Constructeur privé pour empêcher l'instanciation directe
    protected GenericRepository() {}

    // Méthode pour obtenir une instance singleton pour chaque sous-classe
    @SuppressWarnings("unchecked")
    public static <T extends Entity> GenericRepository<T> getInstance(Class<T> clazz) {
        synchronized (instances) {
            // Utilisation de computeIfAbsent pour créer l'instance si elle n'existe pas
            return (GenericRepository<T>) instances.computeIfAbsent(clazz, k -> new GenericRepository<>());
        }
    }

    // Méthode pour sauvegarder une entité

    public void save(T entity) {
        storage.put(entity.getId(), entity);
    }

    // Méthode pour obtenir toutes les entités
    public List<T> getAll() {
        return List.copyOf(storage.values());
    }

    // Méthode pour obtenir une entité par ID
    public T getById(Long id) {
        return storage.get(id);
    }

    // Méthode pour supprimer une entité par ID
    public void delete(Long id) {
        storage.remove(id);
    }
}