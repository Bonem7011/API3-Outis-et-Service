package be.condorcet.alanb.repositories;

import java.util.List;

// T = Le type d'entité (ex: Utilisateur), ID = Le type de la clé primaire (ex: Long)
public interface GenericDAO<T, ID> {

    // Sauvegarder (Create)
    T save(T entity);

    // Trouver par ID (Read)
    T findById(ID id);

    // Tout lister
    List<T> findAll();

    // Mettre à jour (Update)
    T update(T entity);

    // Supprimer (Delete)
    void delete(ID id);
}