package be.condorcet.alanb.repositories;

import be.condorcet.alanb.entities.Utilisateur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped // Une seule instance pour toute l'application (Singleton)
@Transactional // Gère les transactions automatiquement (Phase 4/5)
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    @PersistenceContext(unitName = "AlanBPU") // Le nom qu'on a mis dans persistence.xml !
    private EntityManager em;

    @Override
    public Utilisateur save(Utilisateur entity) {
        em.persist(entity); // INSERT INTO...
        return entity;
    }

    @Override
    public Utilisateur findById(Long id) {
        return em.find(Utilisateur.class, id); // SELECT * FROM... WHERE ID=...
    }

    @Override
    public List<Utilisateur> findAll() {
        // JPQL : On sélectionne des Objets (u), pas des tables (*)
        return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
    }

    @Override
    public Utilisateur update(Utilisateur entity) {
        return em.merge(entity); // UPDATE...
    }

    @Override
    public void delete(Long id) {
        Utilisateur u = findById(id);
        if (u != null) {
            em.remove(u); // DELETE...
        }
    }

    // --- Méthodes Spécifiques ---

    @Override
    public Utilisateur findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // Pas trouvé
        }
    }

    @Override
    public List<Utilisateur> findAdmins() {
        return em.createQuery("SELECT u FROM Utilisateur u WHERE u.isAdmin = true", Utilisateur.class)
                .getResultList();
    }
}