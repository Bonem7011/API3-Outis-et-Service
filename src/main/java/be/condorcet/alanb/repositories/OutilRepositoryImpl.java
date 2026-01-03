package be.condorcet.alanb.repositories;

import be.condorcet.alanb.entities.Outil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;

@ApplicationScoped // Une seule instance pour toute l'application (Singleton)
@Transactional // Gère les transactions automatiquement (Phase 4/5)

public class OutilRepositoryImpl implements OutilRepository {
    @PersistenceContext(unitName = "AlanBPU") // Le nom qu'on a mis dans persistence.xml !
    private EntityManager em;

    @Override
    public Outil save(Outil entity) {
        em.persist(entity); // INSERT INTO...
        return entity;
    }

    @Override
    public Outil findById(Long id) {
        return em.find(Outil.class, id); // SELECT * FROM... WHERE ID=...
    }

    @Override
    public List<Outil> findAll() {
        // JPQL : On sélectionne des Objets (u), pas des tables (*)
        return em.createQuery("SELECT o FROM Outil o", Outil.class).getResultList();
    }

    @Override
    public Outil update(Outil entity) {
        return em.merge(entity); // UPDATE...
    }

    @Override
    public void delete(Long id) {
        Outil o = findById(id);
        if (o != null) {
            em.remove(o); // DELETE...
        }
    }

    @Override
    public List<Outil> findByCategorie(String categorie) {
        try {
            return em.createQuery("SELECT o FROM Outil o WHERE o.categorie = :cat", Outil.class)
                    .setParameter("cat", categorie)
                    .getResultList();
        } catch (Exception e) {
            return null; // Pas trouvé
        }
    }

    @Override
    public List<Outil> findByProprietaireId(Long proprietaireId) {
        // La requête JPQL. Note comment on accède à l'ID du propriétaire (o.proprietaire.id)
        return em.createQuery("SELECT o FROM Outil o WHERE o.proprietaire.id = :propId", Outil.class)
                .setParameter("propId", proprietaireId)
                .getResultList();
    }
}
