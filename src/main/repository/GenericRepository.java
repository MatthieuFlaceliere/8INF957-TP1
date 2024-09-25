package main.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepository<T> {

    // Map pour stocker les instances singleton par type
    private static final Map<Class<?>, GenericRepository<?>> instances = new HashMap<>();
    
    // Stockage interne pour les entités
    private final Map<Long, T> storage = new HashMap<>();

    // Constructeur privé pour empêcher l'instanciation directe
    protected GenericRepository() {}

    // Méthode pour obtenir une instance singleton pour chaque sous-classe
    @SuppressWarnings("unchecked")
    public static <T> GenericRepository<T> getInstance(Class<T> clazz) {
        synchronized (instances) {
            // Si aucune instance n'existe pour la classe donnée, on la crée
            if (!instances.containsKey(clazz)) {
                instances.put(clazz, new GenericRepository<>());
            }
            // Retourne l'instance typée correctement
            return (GenericRepository<T>) instances.get(clazz);
        }
    }

    // Méthode pour sauvegarder une entité

    public void save(Long id, T entity) {
        storage.put(id, entity);
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